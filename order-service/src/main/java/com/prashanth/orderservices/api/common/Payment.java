package com.prashanth.orderservices.api.common;



public class Payment {
		
		private int paymentId;
		private String paymentStatus;
		private String transactionId;
		private int orderId;
		private double amount;
		
		public Payment() {
			super();
		}

		public Payment(int paymentId, String paymentStatus, String transactionId, int orderId, int amount) {
			super();
			this.paymentId = paymentId;
			this.paymentStatus = paymentStatus;
			this.transactionId = transactionId;
			this.orderId = orderId;
			this.amount = amount;
		}

		public int getPaymentId() {
			return paymentId;
		}

		public void setPaymentId(int paymentId) {
			this.paymentId = paymentId;
		}

		public String getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}
		
		
		
		
		
		
		

	}


