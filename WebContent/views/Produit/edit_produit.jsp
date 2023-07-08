<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="Update_produit" id="edit_form" method="POST" role="form">
	<div class="form-group">
	    <label for="">N° Produit</label>
	    <input type="text" class="form-control" name="numProduit_m" value="${produit_to_update.numProduit }" readonly>
	</div>

	<div class="form-group">
	    <label for="">Designation</label>
	    <input type="text" class="form-control" name="design_m" value="${produit_to_update.design }">
	</div>

	<div class="form-group">
		<label for="">Stock</label>
		<input type="text" min="1" class="form-control" name="stock_m" value="${produit_to_update.stock }" readonly>
	</div>

	<input class="btn btn-primary" type="submit" name="action" value="Modifier" />
</form>


<script type="text/javascript" src="Assets/js/jquery.min.js"></script>
<script type="text/javascript" src="Assets/js/jquery.validate.js"></script>
<script>
$().ready(function(){
	$("#edit_form").validate({
		  rules: {
		    design_m: 'required'		    
		  },
		  messages:{
			  design_m : "** La désignation ne peut pas être vide",
		  }
		});

	jQuery.validator.addMethod("math", function(value, element) {
		  return this.optional(element) || value == params[0] + params[1];
		}, "message");
});
</script>