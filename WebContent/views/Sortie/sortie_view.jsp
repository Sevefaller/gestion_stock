<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sortie</title>
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
                        <li><a href="http://localhost:8080/g_stock_hibernate/entree">Entrée</a></li>
                        <li class="active"><a href="http://localhost:8080/g_stock_hibernate/sortie">Sortie</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/index_mvm_controller">Mouvement de stock</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/produit_statistique">Statistique</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        
        <div class="container-fluid">
           	<h1 style="display: inline" class="">Sorties</h1>
        	<hr>
        	<div class="recherche">
        		<form class="form-inline" action="export_sortie_controller" method="POST">
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
        	<c:if test="${msg}">
        		<div class="alert alert-danger">
				  	<strong>Danger!</strong> Indicates a dangerous or potentially negative action.
				</div>
        	</c:if>
        
            <table class="table table-striped table-bordered " >
                <thead>
                    <tr>
                        <th class="col-lg-2">N° Bon Sortie</th>
                        <th class="col-lg-2">Produit</th>
                        <th class="col-lg-3">Quantité sortie</th>
                        <th class="col-lg-3">Date de sortie</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody id="table_produit">
                    <c:forEach items="${model_sortie}" var="s">
                    	<tr>
                    		<td>${s.numBonSortie}</td>
	                        <td>${s.numProduit}</td>
	                        <td>${s.qteSortie}</td>
	                        <td>${s.dateSortie}</td>
	                        <td>
	                            <a id ="${s.numBonSortie}" data-toggle="modal" href='#modal-edit' class="btn btn-info btn-edit">Modifier</a>
	                            <a href="javascript:confirmer('Delete_sortie?numBonSortie=${s.numBonSortie}','${s.numBonSortie}')" class="btn btn-danger">Supprimer</a>
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
                            <h4 class="modal-title">Ajouter sortie</h4>
                        </div>
                        
                        <!-- Formulaire ajout produit -->
                        <div class="modal-body">
                            <form action="Add_sortie" id="add_form" method="POST" role="form">
                                <div class="form-group">
                                    <label for="">N° Sortie</label>
                                    <input type="text" class="form-control" name="numBonSortie">
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
                                    <label for="">Quantité Sortie</label>
                                    <input type="number" min="1" class="form-control" name="qteSortie">
                                </div>
                                
                                <div class="form-group">
                                    <label for="">Date de sortie</label>
                                    <input type="text" class="form-control" id="dateSortie" name="dateSortie">
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
                            <h4 class="modal-title">Modifier sortie</h4>
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
        <script type="text/javascript" src="Assets/js/validationSortie.js"></script>
        <script type="text/javascript">
        	function confirmer(url,num){
        		var rep = confirm("Etes vous sur de vouloir supprimer la sortie " +num+ " ?");
        		if (rep == true){
        			document.location = url;
        		}
        	}
        
        	$(document).ready(function(){
        		$("#dateSortie").datepicker();
        		
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
                    var numBonSortie = $(this).attr("id");
                    var url = "http://localhost:8080/g_stock_hibernate/Update_sortie?numBonSortie=" + numBonSortie;
                    
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