$(function($) {
	'use strict';

	var customerEdit = flight.component(function() {

		this.attributes({
			templateName : 'customer-edit-form',
			editCustomerDivSelector : '.js-edit-customer',
			editCustomerConfirmSelector : '.js-edit-customer-confirm',
			compiledTemplate : '',
			getCustomerUrl : '/client-ui/getCustomer/'
		});

		this.formSettings = {
			fields : {
				firstName : 'empty',
				lastName : 'empty',
				dateOfBirth : 'empty'
			}
		};

		this.loadCustomer = function(id) {
			var that = this;
			$.ajax({
				url : this.attr.getCustomerUrl + id,
				dataType : 'json',
				success : function(data) {
					$(that.attr.editCustomerDivSelector).html(that.attr.compiledTemplate({
						customer : data
					}));
				},
				error : function() {
					console.log('Error getting data from server')
				}
			});
		};

		this.getParameterByName = function(name, url) {
			if (!url)
				url = window.location.href;
			name = name.replace(/[\[\]]/g, "\\$&");
			var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)", "i"), results = regex.exec(url);
			if (!results)
				return null;
			if (!results[2])
				return '';
			return decodeURIComponent(results[2].replace(/\+/g, " "));
		};

		this.saveCustomer = function() {
			$('#edit-customer-form').submit();
		};

		this.after('initialize', function() {
			this.attr.compiledTemplate = Handlebars.getTemplate(this.attr.templateName);
			$('.ui.form').form(this.formSettings);
			$(this.attr.editCustomerConfirmSelector).click(this.saveCustomer);
			this.loadCustomer(this.getParameterByName('id'));
		});

	});

	customerEdit.attachTo('body');
});