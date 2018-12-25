<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>Profil Utilisateur</title>
	
	<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
	</head>
	<body class="ltr">
		<div id="wrapcentre">
	
			<br style="clear: both;" />
				<table class="tablebg" style="margin-top: 5px;" cellspacing="1" cellpadding="0" width="100%">
					<tbody>
						<tr>
							<td class="row1">
								<p class="breadcrumbs">
									Profil de <c:out value="${infos[0]}"/>
								</p>
							</td>
						</tr>
					</tbody>
				</table>
				<br/>
				<c:if test="${ avatar == 'ok' }">
					<strong><p style="color: green;">Votre avatar sera mis à jour dans quelques instants.</p></strong>
				</c:if>
				<c:if test="${ avatar == 'nonok' }">
					<strong><p style="color: red;">Veuillez uploader une image, et non autre chose.</p></strong>
				</c:if>

				<img src="${infos[3]}" style="width:100px; height:100px;"/>
				<br/>
				<table class="tablebg" style="margin-top: 5px; margin-left: 10px;" cellspacing="5" cellpadding="10" width="30%">
					<tbody>
						<tr>
							<td>
								Username : <c:out value="${infos[0]}"/>
							</td>
						</tr>
						<tr>
							<td>
								Date d'inscription : <c:out value="${infos[1]}"/>
							</td>
						</tr>
						<tr>
							<td>
								Nombre de posts : <c:out value="${infos[2]}"/>
							</td>
						</tr>
					</tbody>
				</table>
				<br/>
				<c:if test="${ username != null }">
					<form action="/forum/upload">
						<input type="submit" value="Changer d'avatar" />
					</form>
				</c:if>
				
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
