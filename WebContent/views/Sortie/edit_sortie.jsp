<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="Update_sortie" id ="edit_form" method="POST" role="form">
  <div class="form-group">
        <label for="">N° Sortie</label>
        <input type="text" class="form-control" name="numBonSortie_m" value="${sortie_to_update.numBonSortie}" readonly>
    </div>
    
    <div class="form-group">
        <label>Produit</label>
        <select class="form-control" name="numProduit_m" id="liste_produit" readonly>
             <c:forEach items="${model1}" var="p">
				<option value="${p.numProduit}" ${p.numProduit == produit.numProduit ? 'selected' :'' }>${p.design}</option>
             </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="">Quantité Sortie</label>
        <input type="number" min="1" class="form-control" name="qteSortie_m" value="${sortie_to_update.qteSortie}">
    </div>
    
    <div class="form-group">
        <label for="">Date de Sortie</label>
        <input type="date" class="form-control" id="dateSortie" name="dateSortie_m" value="${sortie_to_update.dateSortie}">
    </div>

    <input class="btn btn-primary" type="submit" name="action" value="Valider" />
</form>

<script type="text/javascript" src="Assets/js/jquery.min.js"></script>
<script type="text/javascript" src="Assets/js/bootstrap.js"></script>
<script type="text/javascript" src="Assets/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="Assets/js/jquery.validate.js"></script>
<script type="text/javascript">       
	$(document).ready(function(){		
		$("#edit_form").validate({
		  rules: {
			qteSortie_m: 'required',
			dateSortie_m : 'required'	
		  },
		  messages:{
			  qteSortie_m : "** La quantité ne peut pas être vide",
			  dateSortie_m : "** La date ne peut pas être vide"
		  }
		});
	});

</script>