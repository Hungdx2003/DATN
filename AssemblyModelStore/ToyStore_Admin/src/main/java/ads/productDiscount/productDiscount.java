package ads.productDiscount;

import java.util.ArrayList;

import ads.objects.ProductDiscount;

public interface productDiscount {
	boolean addProductDiscount(ArrayList<ProductDiscount> items);
	boolean editProductDiscount(ArrayList<ProductDiscount> items);
	boolean delProductDiscount(ArrayList<ProductDiscount> items);
	boolean delProductDiscount(ProductDiscount item);
}
