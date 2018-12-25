<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>Inscription</title>
	
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
									<a href="/forum/home">Board index</a>
								</p>
							</td>
						</tr>
					</tbody>
				</table>
				<br />
	
				<form action="#" method="post">

					<table class="tablebg" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<th colspan="2">Sign Up</th>
							</tr>

							<tr>
								<td class="row2">

									<table style="width: 100%;" cellspacing="1" cellpadding="4" align="center">
										<tbody>
											<tr>
												<td valign="top"><b class="gensmall">Login :</b></td>
												<td><input class="post" name="username" size="25" tabindex="1" type="text" value="${fn:escapeXml(param.username)}"/></td>
											</tr>
											<tr>
												<td valign="top"><b class="gensmall">Mot de passe:</b></td>
												<td><input class="post" name="password" size="25" tabindex="2" type="password" value="${fn:escapeXml(param.password)}"/></td>
												<input type="hidden" name="csrfToken" value="${csrfToken}">
											</tr>

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td class="cat" colspan="2" align="center"><input name="register" class="btnmain" value="Register" tabindex="5" type="submit" /></td>
							</tr>
						</tbody>
					</table>
					<c:if test="${ inscrit == 'non'  }">
						<center><p style="color:red;"><strong>Le couple login/mot de passe n'est pas valide.</strong></p></center>
					</c:if>
					<c:if test="${ inscrit == 'pbCarac'  }">
						<center><p style="color:red;"><strong>Le nom d'utilisateur ne doit pas �tre vide et le mot de passe doit �tre compos� de 8 carac�tres minimum.</strong></p></center>
					</c:if>

				</form>

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
