package fr.acceis.forum.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JeuDeTestJdBc {

	public final static String[] QUERIES = {
			"drop table Utilisateurs if exists",
			"drop table Messages if exists",
			"drop table Fil if exists",
			"create table Messages (idMessage INTEGER IDENTITY PRIMARY KEY, name varchar(255), message varchar(4000), idFil INTEGER, heure varchar(255))",
			"create table Fil (idFil INTEGER IDENTITY PRIMARY KEY, name varchar(255), nbreponse integer, nbvues integer, auteur varchar(255))",
			"create table Utilisateurs (idUser INTEGER IDENTITY PRIMARY KEY, login varchar(255), password varchar(512), posts iNTEGER not null, inscription varchar(255), avatar varchar(255), role INTEGER, salt varchar(25))",
			"INSERT INTO UTILISATEURS (login, password, posts, inscription, avatar, role, salt) VALUES('sina', '62dd9cc5df8237fa5fd8cc4a149d7a0c8fae3a1d24a42fdca0dd73aae48fb2e2', 0, 'Wed Oct 3 13:19:58 CEST 2018', 'fichiers/default.png', 1, 'vFTz7Lzw9vs5LjOOJPh2')",
			"INSERT INTO UTILISATEURS (login, password, posts, inscription, avatar, role, salt) VALUES('admin', '042ad6c6d2f1b495bba5f8061b51f3c16755b0c793bc3d0c982e38840b8c910b', 2, 'Wed Oct 2 13:19:58 CEST 2018', 'fichiers/default.png', 3, 'FiSMVsFg5nqXjbKsj2bM')",
			"INSERT INTO Fil (name, nbreponse, nbvues, auteur) VALUES('mon aventure avec cette fille', 0, 0, 'admin')",
			"INSERT INTO Fil (name, nbreponse, nbvues, auteur) VALUES('règle du forum', 0, 0, 'admin')",
			"INSERT INTO Messages (name, message, idFil, heure) VALUES('admin', 'Elle ma larguer :(', 0, 'Wed Oct 4 13:19:58 CEST 2018')",
			"INSERT INTO Messages (name, message, idFil, heure) VALUES('admin', 'c moi le boss', 1, 'Wed Oct 4 13:19:58 CEST 2018')",
	};

	public static void main(String[] args) throws Exception {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa",  "");
		Statement stmt = connexion.createStatement();

		for (String query : QUERIES) {
			stmt.executeUpdate(query);
		}

		stmt.close();
		connexion.close();
	}
	/*			
			"INSERT INTO UTILISATEURS (login, password, posts, inscription, avatar, role) VALUES('pierre', 'pierre', 1, 'Wed Oct 1 13:19:58 CEST 2018', 'fichiers/default.png', 2)",
			
			"INSERT INTO UTILISATEURS (login, password, posts, inscription, avatar, role) VALUES('jacques', 'jacques', 0, 'Wed Oct 4 13:19:58 CEST 2018', 'fichiers/default.png', 1)",
			*/
}
