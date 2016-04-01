$(function($) {
	'use strict';

	var customersList = flight.component(function() {

		this.attributes({
			templateName : 'customer-table',
			compiledTemplate : '',
			tableBodySelector : '.js-customers-table',
			getCustomersUrl : '/client-ui/getCustomers'
		});

		this.loadCustomers = function() {
			var that = this;
			$.ajax({
				url : this.attr.getCustomersUrl,
				dataType : 'json',
				success : function(data) {
					$(that.attr.tableBodySelector).html(that.attr.compiledTemplate(data._embedded));
				},
				error : function() {
					console.log('Error getting data from server')
				}
			});
		};

		this.after('initialize', function() {
			this.attr.compiledTemplate = Handlebars.getTemplate(this.attr.templateName);
			this.loadCustomers();
		});

	});

	customersList.attachTo('body');
});