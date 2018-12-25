package fr.acceis.forum.servlet;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;

import com.google.common.hash.Hashing;

public class DAO {
	
	public static boolean getUtilisateur(String login, String password) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "SELECT login, password, posts, salt FROM UTILISATEURS WHERE login=?";

		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, login);

		ResultSet resultat = result.executeQuery();

		if(resultat.next()) {
			String hashStock = resultat.getString(2); //On récupère le hash en BDD
			String salt = resultat.getString(4); //On récupère le sel en BDD

			String verif = hashPassword(password, salt); //On recalcule le hash avec le mot de passe fourni 
			if(verif.equals(hashStock)) { //On vérifie que les hash correspondent
				result.close();
				resultat.close();
				JDBC.closeConnection();
				return true;
			}
		}
		JDBC.closeConnection();
		return false;
	}

	public static LinkedList<String[]> getAllFil() throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();
		//Obtenir nom du fil, auteur, nombre de réponse, nombre de vues
		LinkedList<String[]> allFil = new LinkedList<String[]>();

		String query = "SELECT * FROM Fil";

		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);

		ResultSet resultat = result.executeQuery();

		while (resultat.next()) {
			String[] fil = new String[5];
			fil[0] = resultat.getString(2); //Nom du fil
			fil[1] = resultat.getString(3); //Nombre de réponse
			fil[2] = resultat.getString(4); //Nombre de vues
			fil[3] = resultat.getString(5); //Auteur
			fil[4] = resultat.getString(1); //id du Fil

			allFil.add(fil);
		}
		result.close();
		resultat.close();
		JDBC.closeConnection();
		return allFil;
	}

	public static LinkedList<String[]> getAllMessage(String idFil) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		LinkedList<String[]> messages = new LinkedList<String[]>();
		//faire map (auteur, nbposts, heure/message)
		String query = "SELECT * FROM Messages WHERE idFil=? ORDER BY idMessage";

		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, idFil);

		ResultSet resultat = result.executeQuery();

		while (resultat.next()) {
			//Auteur-Heure-NBPosts-NomFil
			String [] AHNBP = new String[8];
			AHNBP[0] = resultat.getString(2); //Auteur
			AHNBP[1] = resultat.getString(5); //Heure
			String msg = resultat.getString(3); //Message
			String Fil = resultat.getString(4); //idFil
			String idMsg = resultat.getString(1); //idMsg

			query = "SELECT posts, avatar FROM UTILISATEURS WHERE login=?";
			result = JDBC.getConnexion().prepareStatement(query);
			result.setString(1, AHNBP[0]);

			ResultSet nbPostsAvatar = result.executeQuery();
			nbPostsAvatar.next();
			AHNBP[2] = nbPostsAvatar.getString(1); //NBPosts
			AHNBP[6] = nbPostsAvatar.getString(2); //Avatar

			query = "SELECT name FROM Fil WHERE idFil=?";
			result = JDBC.getConnexion().prepareStatement(query);
			result.setString(1, Fil);

			ResultSet nameFil = result.executeQuery();
			nameFil.next();
			AHNBP[3] = nameFil.getString(1); //Nom du Fil

			AHNBP[4] = msg;
			AHNBP[5] = Fil;
			AHNBP[7] = idMsg;
			messages.add(AHNBP);
		}
		result.close();
		resultat.close();
		JDBC.closeConnection();
		return messages;
	}

	public static void incVues(String idFil) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "UPDATE Fil SET nbvues=nbvues+1 WHERE idFil=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, idFil);

		result.executeUpdate();

		result.close();
		JDBC.closeConnection();
	}

	public static void incRep(String idFil) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "UPDATE Fil SET nbreponse=nbreponse+1 WHERE idFil=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, idFil);

		result.executeUpdate();
		result.close();
		JDBC.closeConnection();
	}

	public static void decRep(String idFil) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "SELECT nbreponse FROM Fil WHERE idFil=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, idFil);

		ResultSet tmp = result.executeQuery();
		tmp.next();
		if(Integer.parseInt(tmp.getString(1)) > 0) {

			query = "UPDATE Fil SET nbreponse=nbreponse-1 WHERE idFil=?";
			result = JDBC.getConnexion().prepareStatement(query);
			result.setString(1, idFil);

			result.executeUpdate();
		}
		result.close();
		JDBC.closeConnection();
	}


	public static void incNBPost(String idUser) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "UPDATE Utilisateurs SET posts=posts+1 WHERE idUser=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, idUser);

		result.executeUpdate();
		result.close();
		JDBC.closeConnection();
	}

	public static void incNBPostViaLogin(String login) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "UPDATE Utilisateurs SET posts=posts+1 WHERE login=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, login);

		result.executeUpdate();
		result.close();
		JDBC.closeConnection();
	}

	public static void decNBPostViaLogin(String login) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "UPDATE Utilisateurs SET posts=posts-1 WHERE login=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, login);

		result.executeUpdate();
		result.close();
		JDBC.closeConnection();
	}

	public static boolean register(String username, String password) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		Calendar calendar = Calendar.getInstance();
		String date = calendar.getTime().toString();

		String query = "SELECT login FROM UTILISATEURS WHERE login=?";
		PreparedStatement resultat = JDBC.getConnexion().prepareStatement(query);
		resultat.setString(1, username);

		ResultSet result = resultat.executeQuery();
		if(!result.next()) {
			String salt = genSalt();
			String pass = hashPassword(password, salt);
			query = "INSERT INTO UTILISATEURS (login, password, posts, inscription, avatar, role, salt) VALUES(?, ?, 0, ?, 'fichiers/default.png', 1, ?)";
			resultat = JDBC.getConnexion().prepareStatement(query);
			resultat.setString(1, username);
			resultat.setString(2, pass);
			resultat.setString(3, date);
			resultat.setString(4, salt);

			resultat.executeUpdate();
			result.close();
			resultat.close();
			JDBC.closeConnection();
			return true;
		}
		result.close();
		resultat.close();
		JDBC.closeConnection();
		return false;
	}

	public static void newTopic(String name, String corps, String auteur) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();
		String query = "INSERT INTO Fil (name, nbreponse, nbvues, auteur) VALUES(?, 0, 0, ?)";
		PreparedStatement resultat = JDBC.getConnexion().prepareStatement(query);
		resultat.setString(1, name);
		resultat.setString(2, auteur);

		resultat.executeUpdate();

		query = "SELECT idFil FROM Fil WHERE name=? AND nbreponse=0 AND nbvues=0 AND auteur=?";
		resultat = JDBC.getConnexion().prepareStatement(query);
		resultat.setString(1, name);
		resultat.setString(2, auteur);

		ResultSet result = resultat.executeQuery();
		result.next();
		String idFil = result.getString(1);

		incNBPostViaLogin(auteur);

		Calendar calendar = Calendar.getInstance();
		String date = calendar.getTime().toString();

		query = "INSERT INTO Messages (name, message, idFil, heure) VALUES(?, ?, ?, ?)";
		resultat = JDBC.getConnexion().prepareStatement(query);
		resultat.setString(1, auteur);
		resultat.setString(2, corps);
		resultat.setString(3, idFil);
		resultat.setString(4, date);

		resultat.executeUpdate();
		result.close();
		resultat.close();
		JDBC.closeConnection();
	}

	public static void newResponse(String idFil, String msg, String auteur) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "SELECT idUser FROM Utilisateurs WHERE login=?";
		PreparedStatement resultat = JDBC.getConnexion().prepareStatement(query);
		resultat.setString(1, auteur);

		ResultSet result = resultat.executeQuery();
		result.next();
		String idUser = result.getString(1);

		incNBPost(idUser);

		Calendar calendar = Calendar.getInstance();
		String date = calendar.getTime().toString();

		query = "INSERT INTO Messages (name, message, idFil, heure) VALUES(?, ?, ?, ?)";	
		resultat = JDBC.getConnexion().prepareStatement(query);
		resultat.setString(1, auteur);
		resultat.setString(2, msg);
		resultat.setString(3, idFil);
		resultat.setString(4, date);

		resultat.executeUpdate();
		result.close();
		resultat.close();
		JDBC.closeConnection();
	}

	public static String[] getInformations(String login) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();
		String[] resultat = new String[4];

		String query = "SELECT login, inscription, posts, avatar FROM Utilisateurs WHERE login=?";
		PreparedStatement resul = JDBC.getConnexion().prepareStatement(query);
		resul.setString(1, login);

		ResultSet result = resul.executeQuery();
		if(!result.next()) {
			return null;
		}
		resultat[0] = result.getString(1); //login
		resultat[1] = result.getString(2); //inscription
		resultat[2] = result.getString(3); //posts
		resultat[3] = result.getString(4); //avatar

		JDBC.closeConnection();
		result.close();
		resul.close();
		return resultat;
	}

	public static void updateAvatar(String login, String path) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "UPDATE Utilisateurs SET avatar=? WHERE login=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, path);
		result.setString(2, login);

		result.executeUpdate();
		JDBC.closeConnection();
		result.close();
	}

	public static boolean exist(String login) throws Exception{
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "SELECT * FROM UTILISATEURS WHERE login=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, login);

		ResultSet resultat = result.executeQuery();
		if(resultat.next()) {
			result.close();
			resultat.close();
			JDBC.closeConnection();
			return true;
		}
		result.close();
		resultat.close();
		JDBC.closeConnection();
		return false;
	}

	public static String getRole(String login) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();
		String role = null;

		String query = "SELECT role FROM UTILISATEURS WHERE login=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, login);

		ResultSet resultat = result.executeQuery();
		if(resultat.next()) {
			role = resultat.getString(1);
			result.close();
			resultat.close();
			JDBC.closeConnection();
			return role;
		}
		result.close();
		resultat.close();
		JDBC.closeConnection();
		return role;
	}

	public static void supprimerMsg(String idMsg, String idFil) throws Exception {
		JDBCUtils JDBC = new JDBCUtils();
		JDBC.openConnection();

		String query = "SELECT name FROM Messages WHERE idMessage=?";
		PreparedStatement result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, idMsg);

		ResultSet tmp = result.executeQuery();
		tmp.next();

		DAO.decNBPostViaLogin(tmp.getString(1));

		query = "DELETE FROM Messages WHERE idMessage=?";
		result = JDBC.getConnexion().prepareStatement(query);
		result.setString(1, idMsg);

		result.executeUpdate();

		DAO.decRep(idFil);
	}

	private static String hashPassword(String password, String salt) throws Exception {
		String sha256hex = Hashing.sha256().hashString((salt+password), StandardCharsets.UTF_8).toString();
		return sha256hex;
	}

	private static String genSalt() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String pass = "";
		for(int x=0;x<20;x++) {
			int i = (int)Math.floor(Math.random() * 62);
			pass += chars.charAt(i);
		}
		return pass;
	}
}
