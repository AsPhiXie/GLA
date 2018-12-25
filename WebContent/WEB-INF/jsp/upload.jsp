<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>Réponse</title>
	
	
	
	<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
	</head>
	<body class="ltr">
	
	
		<div id="wrapcentre">
	
			<div id="pagecontent">
	
				<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
					cellpadding="0" width="100%">
					<tbody>
						<tr>
							<td class="row1">
							<!--  Nom d'utilisateur si connecté -->
								<p class="breadcrumbs">Bienvenue, 
								<c:choose>
									<c:when test="${ username != null }">
										<c:out value="${username}"/></p>
										<strong><a href="/forum/login?logout=1"style="margin-left: 80%;">Déconnexion</a> | <a href="/forum/profil">Mon Profil</a></strong>
									</c:when>
									<c:otherwise>
										Invité</p>
										<strong><a href="/forum/login" style="margin-left: 86%;">Connexion</a>
										<a href="/forum/register">Inscription</a></strong>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${ badSize == 'oui'  }">
					<center><strong><p style="color:red;">La taille de l'avatar ne doit pas dépasser 2MB.</p></strong></center>
				</c:if>
				<br clear="all" />
				Veuillez sélectionner votre avatar:<br/><br/>
				<form method="post" enctype="multipart/form-data">
					<input type="file" name="avatar"/><br/><br/><br/>
					<input type="submit" value="Envoyer"/>
				</form>
				
				<table class="tablebg" style="margin-top: 15px;" cellspacing="1" cellpadding="0" width="100%">
					<tbody>
						<tr>
							<td class="row1">
								<p class="breadcrumbs"><a href="/forum/home">Index du forum</a></p>
							</td>
						</tr>
					</tbody>
				</table>
	
		</div>
	</div>
	
	</body>
</html>
