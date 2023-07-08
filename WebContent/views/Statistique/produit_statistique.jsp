<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistique Produit</title>
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
                        <li><a href="http://localhost:8080/g_stock_hibernate/index_mvm_controller">Mouvement de stock</a></li>
                        <li class="active"><a href="http://localhost:8080/g_stock_hibernate/produit_statistique">Statistique</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        
        <div class="container-fluid">
           	<div class="form-inline">
           		<div class="form-group"><h1 class="">Statistique</h1></div>
	           	<div class="form-group ">
	           		
	           	</div>
           	</div>
           	<hr>
        	<div>
        		<form class="form-inline pull-right" action="export_produit_PDF" method="POST">				  	
				  	<div class="form-group" class="add">
					   <select name="choice" class="form-control " id="choice">
	           			<option value="pie">Pie</option>
		           		<option value="column">Column</option>
		           		<option value="bar">Bar</option>
		           		<option value="line">Line</option>
		           		<option value="area">Area</option>
		           	</select>
				  	</div> 
				</form>
        	</div>
        </div>
        
        <hr>
        
       <div id="chartContainer" class="container"></div>
       
       <script type="text/javascript" src="Assets/js/jquery.min.js"></script>
       <script type="text/javascript" src="Assets/js/bootstrap.js"></script>
       <script type="text/javascript" src="Assets/js/canvasjs.min.js"></script>
      	<script type="text/javascript">
	       		$().ready(function(){
	       			renderChart("pie");
	       			
	       			$("#choice").change(function(){
	       				renderChart($("#choice").val());
	       			});
	       			
	       			function renderChart(choice){
	       				var chart = new CanvasJS.Chart("chartContainer", {
			                animationEnabled: true,
			                exportEnabled: true,
			                title: {
			                        text: "Etat de stock"
			                },
			                data: [{
			                        type: choice, //change type to column,bar, line, area, pie, etc
			                        dataPoints: ${dataPoints}
			                }]
				        });
				        chart.render();
	       			}
	       		});
      	 </script>
      
	</body>
</html>