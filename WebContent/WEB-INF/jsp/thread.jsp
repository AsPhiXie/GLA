<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>THREAD</title>
	
	
	
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
					</tbody>
				</table>
	
				<br clear="all" />
	
				<table cellspacing="1" width="100%">
					<tbody>
						<tr>
							<td valign="middle" align="left" colspan="4" nowrap="nowrap"><a href="/forum/newTopic"><img src="fichiers/button_topic_new.gif" alt="Post new topic" title="Post new topic" /></a>&nbsp;<a href="/forum/response"><img src="fichiers/button_topic_reply.gif" alt="Reply to topic" title="Reply to topic" /></a>
							</td>
						</tr>
					</tbody>
				</table>
	
				<br clear="all" />
				
				<c:forEach items="${message}" var="entry">
					<table class="tablebg" cellspacing="1" width="100%">
					<tbody>
						<tr class="row2">
	
							<td valign="middle" align="center"><b class="postauthor">
								<img src="${entry[6]}" style="width:50px; height:50px;"/> <br/><a href="/forum/profil?login=<c:out value="${entry[0]}"/>"><c:out value="${entry[0]}"/></a></b>
							</td>
							<td width="100%" height="25">
								<table cellspacing="0" width="100%">
									<tbody>
										<tr>
	
											<td class="gensmall" width="100%">
												<div style="float: left;">&nbsp;
													<b>Fil de discussion :</b> <c:out value="${entry[3]}"/>
												</div>
												<div style="float: right;">
													<b>Posted:</b> <c:out value="${entry[1]}" escapeXml="true"/> 
													<c:if test="${ role == 2 || role == 3 }">
														 | <strong><a href="/forum/supprimer?idMsg=${entry[7]}">Supprimer</a></strong>
													</c:if>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
	
					<tr class="row2">
	
						<td class="profile" valign="top">
							<table cellspacing="4" align="center" width="150">
	
							</table> <span class="postdetails"> <b>Posts:</b> <c:out value="${entry[2]}" escapeXml="true"/><br/>
							
						</span>
	
						</td>
						<td valign="top">
							<table cellspacing="5" width="100%">
								<tbody>
									<tr>
										<td>
											<div class="postbody"><c:out value="${entry[4]}" escapeXml="true"/></div>
											<br clear="all" /><br />
											<table cellspacing="0" width="100%">
												<tbody>
													<tr valign="middle">
														<td class="gensmall" align="right"></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
	
					<tr>
						<td class="spacer" colspan="2" height="1"><img src="fichiers/spacer.gif" alt="" width="1" height="1" /></td>
					</tr>
				</table>
				</c:forEach>
			</div>
		
			<br clear="all" />
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
