<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mouvement de Stock</title>
        <link rel="stylesheet" href="Assets/css/bootstrap.css">
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
                        <li><a href="http://localhost:8080/g_stock_hibernate/sortie">Sortie</a></li>
                        <li class="active"><a href="http://localhost:8080/g_stock_hibernate/index_mvm_controller">Mouvement de stock</a></li>
                        <li><a href="http://localhost:8080/g_stock_hibernate/produit_statistique">Statistique</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        
        <div class="container-fluid">
           	<h1 style="display: inline" class="">Mouvement de stock</h1>
        </div>
        
        <hr>
        
        <div class="container-fluid">
	        <form action="index_mvm_controller" method="POST" class="form-inline" role="form" id="check_form"  class="recherche">		
				<div class="form-group">
                     <label>Produit : </label>
                     <select class="form-control" name="numProduit" id="numProduit">
                          <c:forEach items="${model_produit}" var="p">
                             <option value="${p.numProduit}">${p.design}</option>
                          </c:forEach>
                     </select>
                 </div>
                 
				<div class="form-group" id="div_choix">
                     <label>Choix : </label>
                     <select class="form-control" name="choix">
                        <option value="tous">Tous</option>
                        <option value="deux_dates">Entre deux date</option>
                        <option value="an">Par année</option>
                        <option value="mois">Par mois</option>
                     </select>
                 </div>
                 
                 <div class="form-group" id="date_1">
                     <label>Date 1 : </label>
                     <input type="text" class="form-control" id="date1" name="date1">
                 </div>
                 
                 <div class="form-group" id="date_2">
                     <label>Date 2 : </label>
                     <input type="text" class="form-control" id="date2" name="date2">
                 </div>
                 
                 <div class="form-group" id="div_mois">
                     <label>Mois : </label>
                     <select class="form-control" name="mois">
                     	<option></option>
                     	<option value="1">Janvier</option>
                     	<option value="2">Fevrier</option>
                     	<option value="3">Mars</option>
                     	<option value="4">Avril</option>
                     	<option value="5">Mai</option>
                     	<option value="6">Juin</option>
                     	<option value="7">Juillet</option>
                     	<option value="8">Août</option>
                     	<option value="9">Septembre</option>
                     	<option value="10">Octobre</option>
                     	<option value="11">Novembre</option>
                     	<option value="12">Décembre</option>
                     </select>	
                 </div>
                 
                 <div class="form-group" id="div_annee">
                     <label>Année : </label>
                     <select class="form-control" name="an">
                     	<option></option>
                     	<c:forEach var="i" begin="2008" end="2022">
                     		<option value="<c:out value="${i}" />"><c:out value="${i}" /></option>
                     	</c:forEach>
                     </select>	
                 </div>
                 
                 <input type="text" name="choix" hidden id="champ_choix" value="tous"/>
				
				<button type="submit" class="btn btn-primary pull-right" id="recherche">Rechercher</button>
			</form>
        </div>
        
        <hr>
        
        <div class="container">
        	<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Numero de bon</th>
						<th>Quantité entrée</th>
						<th>Quantité sortie</th>
						<th>Date</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${model.mvm}" var="m">
                    	<tr>
	                        <td>${m.num}</td>
	                        <td>${m.qtEntree == 0? "": m.qtEntree}</td>
	                        <td>${m.qteSortie == 0? "" : m.qteSortie }</td>
	                        <td>${m.date}</td>
	                    </tr>
                    </c:forEach>	
				</tbody>
			</table>
			
			<div class="container">
				<div class="pull-right">
					<p><strong>Total des entrées : ${totalE }</strong></p>
					<p><strong>Total des sorties : ${totalS }</strong></p>
				</div>
			</div>
        </div>
        
        <script type="text/javascript" src="Assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="Assets/js/bootstrap.js"></script>
        <script type="text/javascript" src="Assets/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="Assets/js/jquery.validate.js"></script>
        <script type="text/javascript">
        	$().ready(function(){
        		$("#date1").datepicker();
        		$("#date2").datepicker();
        		select_change();
        		        		        		
        		$("#div_choix").change(function(){
        			select_change();
        		});
        		
        		$("#numProduit").change(function(){
        			console.log($(".stock_actuel").val());
        		});
        		
        		
        		function select_change(){
        			var choix = $("#div_choix select").val();
        			if(choix == "tous"){
        				$("#div_mois").hide();
                		$("#div_annee").hide();
                		$("#date_1").hide();
                		$("#date_2").hide();
                		$("#champ_choix").val("tous");
        			}
        			else if(choix == "an"){
        				$("#div_mois").hide();
                		$("#div_annee").show();
                		$("#date_1").hide();
                		$("#date_2").hide();
                		$("#champ_choix").val("an");
        			}
        			else if(choix == "mois"){
        				$("#div_mois").show();
                		$("#div_annee").hide();
                		$("#date_1").hide();
                		$("#date_2").hide();
                		$("#champ_choix").val("mois");
        			}
        			else if(choix == "deux_dates"){
        				$("#div_mois").hide();
                		$("#div_annee").hide();
                		$("#date_1").show();
                		$("#date_2").show();
                		$("#champ_choix").val("deux_dates");
        			}
        		}
        		
        		//Validation
        		$("#check_form").validate({
        			  rules: {
        				  date1: "required",
        				  date2 : {
        					  		required : true,
        					  		isAfterStartDate : true
        				  		  },
        				  mois: "required",
        				  an : "required"
        			  },
        			  messages:{
        				  date1 : "** La date 1 ne peut pas être vide",
        				  date2 : {
        					  required :'** La date 2 ne peut pas être vide',
        					  isAfterStartDate : "La date 1 doit inférieure avant la date 2"
        				  },
        				  mois : "** Le mois ne peut pas être vide",
        				  an : "** L'année ne peut pas être vide"
        			  }
        			});
        		
        		jQuery.validator.addMethod("isAfterStartDate", function(value, element,params) {
        	        return isAfterStartDate($('#date1').val(), value);
        	    }, "La date 1 doit être avant la date 2");
        		
        		var isAfterStartDate = function(startDateStr, endDateStr) {
                    var inDate = new Date(startDateStr),
                        eDate = new Date(endDateStr);

                    if(inDate > eDate) {
                        return false;
                    }
                    else
                    	return true;
                };                
        	});
        	
        	
        </script>
	</body>
</html>