$().ready(function(){
	$("#add_form").validate({
		  rules: {
		    numProduit: "required",
		    design: 'required',
		    stock : 'required'
		  },
		  messages:{
			  numProduit : "** Le numero de produit ne peut pas être vide",
			  design : "** La désignation ne peut pas être vide",
			  stock : "** Le stock ne peut pas être vide"
		  }
		});

	jQuery.validator.addMethod("math", function(value, element) {
		  return this.optional(element) || value == params[0] + params[1];
		}, "message");
});