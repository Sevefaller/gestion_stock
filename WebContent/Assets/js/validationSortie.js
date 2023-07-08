$().ready(function(){
	$("#add_form").validate({
		  rules: {
			  numBonSortie: "required",
			  numProduit : "required",
			  qteSortie: 'required',
			  dateSortie : 'required'
		  },
		  messages:{
			  numBonSortie : "** Le numero de bon de sortie ne peut pas être vide",
			  numProduit : "** Le numero de produit ne peut pas être vide",
			  qteSortie : "** La quantité ne peut pas être vide",
			  dateSortie : "** La date ne peut pas être vide"
		  }
		});

	jQuery.validator.addMethod("math", function(value, element) {
		  return this.optional(element) || value == params[0] + params[1];
		}, "message");
});