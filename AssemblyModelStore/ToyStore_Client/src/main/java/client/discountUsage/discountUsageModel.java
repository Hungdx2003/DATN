package client.discountUsage;

import client.objects.DiscountUsage;

public class discountUsageModel {
	private discountUsage du;

	public discountUsageModel() {
		this.du = new discountUsageImpl();
	}

	public boolean addDiscountUsage(DiscountUsage item) {
		return this.du.addDiscountUsage(item);
	}

	public boolean editDiscount(DiscountUsage item) {
		return this.du.editDiscountUsage(item);
	}

	public boolean delDiscount(DiscountUsage item) {
		return this.du.delDiscountUsage(item);
	}
}
