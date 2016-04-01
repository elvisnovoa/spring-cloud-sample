$(function($) {
	'use strict';

	var customersAdd = flight.component(function() {

		this.attributes({
			addCustomerButtonSelector : '.js-add-customer-confirm'
		});

		this.showModal = function(ev) {
			$('.ui.modal').modal({
				onApprove : function() {
					$('#add-customer-form').submit();
					return false;
				}
			}).modal('show');
		};

		this.formSettings = {
			fields : {
				firstName : 'empty',
				lastName : 'empty',
				dateOfBirth : 'empty'
			},
			onSuccess : function(ev, fields) {
				console.log("Valid Submission, modal will close.");
				$('.modal').modal('hide');
			}
		};

		this.after('initialize', function() {
			this.on('click', this.showModal);
			$('#add-customer-form').form(this.formSettings);
			$(this.attr.addCustomerButtonSelector).click(this.addCustomer);
		});

	});

	customersAdd.attachTo('.js-add-customer-modal');
});