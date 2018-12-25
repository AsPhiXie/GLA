<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>FORUM</title>
	
	
	
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
	
				<br clear="all" />
	
					<table cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td valign="middle" align="left"><a href="/forum/newTopic"><img src="fichiers/button_topic_new.gif" alt="Post new topic" title="Post new topic" /></a></td>
							</tr>
						</tbody>
					</table>
	
				<br clear="all" />
	
					<table class="tablebg" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td class="cat" colspan="4">
									<table cellspacing="0" width="100%">
										<tbody>
											<tr class="nav">
												<td valign="middle">&nbsp;</td>
												<td valign="middle" align="right">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<c:if test="${ nondroit == 'oui'  }">
								<center><strong><p style="color:red;">Vous n'avez pas les droits nécessaire.</p></strong></center>
							</c:if>
							<c:if test="${ token == 'oui'  }">
								<center><strong><p style="color:red;">Bad CSRF Token.</p></strong></center>
							</c:if>
							<c:if test="${ ExpiToken == 'oui'  }">
								<center><strong><p style="color:red;">CSRF Token Expired.</p></strong></center>
							</c:if>
							<tr>
							<!-- jsp for each on list -->
	
								<th>&nbsp;Topics&nbsp;</th>
								<th>&nbsp;Auteur&nbsp;</th>
								<th>&nbsp;R&eacute;ponses&nbsp;</th>
								<th>&nbsp;Vues&nbsp;</th>
							</tr>
							<c:forEach items="${fil}" var="entry">
							
							<tr>
								<td class="row1"><a class="topictitle" href="/forum/thread?idFil=${entry[4]}" >${entry[0]}</a></td>
								<td class="row2" align="center" width="130"><p class="topicauthor"><a class="username-coloured" href="#">${entry[3]}</a></p></td>
								<td class="row1" align="center" width="50"><p class="topicdetails">${entry[1]}</p></td>
								<td class="row2" align="center" width="50"><p class="topicdetails">${entry[2]}</p></td>
							</tr>
							
							</c:forEach>
	
						</tbody>
					</table>
					<br clear="all" />
			</div>
	
			<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
				cellpadding="0" width="100%">
				<tbody>
					<tr>
						<td class="row1">
							<p class="breadcrumbs"><a href="/forum/home">Index du forum</a></p>
						</td>
					</tr>
				</tbody>
			</table>
	
		</div>
	
	</body>
</html>
