package ads.productDiscount;

import java.util.ArrayList;

import ads.objects.ProductDiscount;

public class productDiscountModel {
	private productDiscount d;

	public productDiscountModel() {
		this.d = new productDiscountImpl();
	}

	public boolean addProductDiscount(ArrayList<ProductDiscount> items) {
		return this.d.addProductDiscount(items);
	}

	public boolean editProductDiscount(ArrayList<ProductDiscount> items) {
		return this.d.editProductDiscount(items);
	}

	public boolean delProductDiscount(ArrayList<ProductDiscount> items) {
		return this.d.delProductDiscount(items);
	}
}
