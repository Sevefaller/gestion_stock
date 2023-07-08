<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entree</title>
        <link rel="stylesheet" href="Assets/css/bootstrap.css">
        <link rel="stylesheet" href="Assets/css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="Assets/css/style.css">
    </head>
	
	<body>
		<header>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                    <a class="navbar-brand" href="http://localhost:8080/g_stock_hibernate/">Gestion STOCK</a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="http://localhost:8080/g_stock_hibernate/produit">Etat de stock</a></li>
                        <li class="active"><a href="http://localhost:8080/g_stock_hibernate/entree">Entrée</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/sortie">Sortie</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/index_mvm_controller">Mouvement de stock</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/produit_statistique">Statistique</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        
        <div class="container-fluid">
           	<h1 style="display: inline" class="">Entrees</h1>
           	<hr>
        	<div class="recherche">
        		<form class="form-inline" action="export_entree_controller" method="POST">
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
        
        <div class="container">
            <table class="table table-striped table-bordered " >
                <thead>
                    <tr>
                        <th class="col-lg-2">N° Bon Entrée</th>
                        <th class="col-lg-2">Produit</th>
                        <th class="col-lg-3">Quantité entrée</th>
                        <th class="col-lg-3">Date d'entrée</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody id="table_produit">
                    <c:forEach items="${model_entree}" var="e">
                    	<tr>
                    		<td>${e.numBonEntree}</td>
	                        <td>${e.numProduit}</td>
	                        <td>${e.qteEntree }</td>
	                        <td>${e.dateEntree }</td>
	                        <td>
	                            <a id ="${e.numBonEntree}" data-toggle="modal" href='#modal-edit' class="btn btn-info btn-edit">Modifier</a>
	                            <a href="javascript:confirmer('Delete_entree?numBonEntree=${e.numBonEntree}','${e.numBonEntree}')" class="btn btn-danger">Supprimer</a>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <!-- Fenetre d'ajout -->
            <div class="modal fade" id="modal-add">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Ajouter entrée</h4>
                        </div>
                        
                        <!-- Formulaire ajout produit -->
                        <div class="modal-body">
                            <form action="Add_entree" id="add_form" method="POST" role="form">
                                <div class="form-group">
                                    <label for="">N° Entree</label>
                                    <input type="text" class="form-control" name="numBonEntree" style="float:right">
                                </div>
                                
                                <div class="form-group">
                                    <label>Produit</label>
                                    <select class="form-control" name="numProduit">
                                         <c:forEach items="${model_produit}" var="p">
                                            <option value="${p.numProduit}">${p.design}</option>
                                         </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="">Quantité Entrée</label>
                                    <input type="number" min="1" class="form-control" name="qteEntree">
                                </div>
                                
                                <div class="form-group">
                                    <label for="">Date d'Entrée</label>
                                    <input type="text" class="form-control" id="dateEntree" name="dateEntree">
                                </div>

                                <input class="btn btn-primary" type="submit" name="action" value="Enregistrer" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Fenetre de modif -->
            <div class="modal fade" id="modal-edit">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button id="modal-edit-close" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Modifier entrée</h4>
                        </div>
                        
                        <!-- Formulaire modif produit -->
                        <div class="modal-body" id="modal-edit-body">
                            <!-- Ajouter par ajax -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
		<script type="text/javascript" src="Assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="Assets/js/bootstrap.js"></script>
        <script type="text/javascript" src="Assets/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="Assets/js/jquery.validate.js"></script>
        <script type="text/javascript" src="Assets/js/validationEntree.js"></script>
        <script type="text/javascript">
        	function confirmer(url,num){
        		var rep = confirm("Etes vous sur de vouloir supprimer l'entrée " +num+ " ?");
        		if (rep == true){
        			document.location = url;
        		}
        	}
        
        	$(document).ready(function(){
        		$("#dateEntree").datepicker();
        		
        		$("#recherche").keyup(function(){
        			search_table($(this).val());
        		});
        		
        		function search_table(value){
        			$("#table_produit tr").each(function(){
        				var found = false;
        				$(this).each(function(){
        					if($(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
        						found = true;
        				});
        				
        				if (found == true){
        					$(this).show();
        				}
        				else
        					$(this).hide();
        			});
        		}
        		
        		//get info produit a modifier
        		$(".btn-edit").click(function(){
        			$("#modal-edit-body").empty();
                    var numBonEntree = $(this).attr("id");
                    var url = "http://localhost:8080/g_stock_hibernate/Update_entree?numBonEntree=" + numBonEntree;
                    
                    $.get(url, function(data){
                        //Ajouter le formulaire avec les données dans le modal
                       $("#modal-edit-body").append(data);
                    });
                                       
                   $("#modal-edit-close").click(function(){
                       //Vider le formulaire quand on ferme le modal
                       $("#modal-edit-body").empty();
                    });
                 });
        		
        	});
        
        </script>
	</body>
</html>