$().ready(function(){
	$("#add_form").validate({
		  rules: {
			  numBonEntree: "required",
			  numProduit : "required",
			  qteEntree: 'required',
			  dateEntree : 'required'
		  },
		  messages:{
			  numBonEntree : "** Le numero de bon d'entrée ne peut pas être vide",
			  numProduit : "** Le numero de produit ne peut pas être vide",
			  qteEntree : "** La quantité ne peut pas être vide",
			  dateEntree : "** La date ne peut pas être vide"
		  }
		});

	jQuery.validator.addMethod("math", function(value, element) {
		  return this.optional(element) || value == params[0] + params[1];
		}, "message");
});