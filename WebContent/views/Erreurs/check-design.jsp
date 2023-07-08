<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Error</title>
		<link rel="stylesheet" href="Assets/css/bootstrap.css">
        <link rel="stylesheet" href="Assets/css/style.css">
		<link rel="stylesheet" href="Assets/css/sweetalert.css">
	</head>
	
	<body>
		<header>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                    <a class="navbar-brand" href="http://localhost:8080/g_stock_hibernate/">Gestion STOCK</a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="http://localhost:8080/g_stock_hibernate/produit">Etat de stock</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/entree">Entrée</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/sortie">Sortie</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/index_mvm_controller">Mouvement de stock</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/produit_statistique">Statistique</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        
        <div class="container-fluid">
           	<h1 style="display: inline" class="">Etat de stock</h1>
           	<hr>
        	<div class="recherche">
        		<form class="form-inline" action="export_produit_PDF" method="POST">
					<div class="form-group">
					    <input type="text" class="form-control" placeholder="Rechercher" id="recherche" name="recherche">
				  	</div> 
				  	
				  	<div class="form-group export">
					    <input type="submit" class="btn btn-primary" value="Exporter en PDF"/>
				  	</div> 
				  	
				  	<div class="form-group add">
					    <a class="btn btn-primary pull-right" data-toggle="modal" href='#modal-add'>Ajouter</a>
				  	</div> 
				</form>
        	</div>
        </div>
        
        <hr>
	
		<script type="text/javascript" src="Assets/js/jquery.min.js"></script>
		<script type="text/javascript" src="Assets/js/sweetalert.js"></script>		
			
		<script type="text/javascript">
			$(document).ready(function(){
				swal({
				  title: "Erreur!",
				  text: "Désignation de produit existe déjà, veuillez reessayer",
				  type : "error"
				},
				function(){
					location.href = "http://localhost:8080/g_stock_hibernate/produit";
				});
			});
		</script>
	</body>
</html>