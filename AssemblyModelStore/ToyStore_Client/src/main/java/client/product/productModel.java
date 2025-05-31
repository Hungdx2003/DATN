package client.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.objects.ProductObject;
import client.viewModel.productViewModel;

public class productModel {
	private product p;

	public productModel() {
		this.p = new productImpl();
	}

	public boolean addProduct(ProductObject item) {
		return this.p.addProduct(item);
	}

	public boolean editProduct(ProductObject item) {
		return this.p.editProduct(item);
	}

	public boolean delProduct(ProductObject item) {
		return this.p.delProduct(item);
	}

	public ProductObject getProduct(int id) {
		ProductObject item = null;

		ResultSet rs = this.p.getProduct(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_original_price(rs.getInt("product_original_price"));
					item.setProduct_quantity(rs.getInt("product_quantity"));
					item.setProduct_created_by(rs.getInt("product_created_by"));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_by(rs.getInt("product_modified_by"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_pc_id(rs.getInt("product_pc_id"));
					item.setProduct_detail(rs.getString("product_detail"));
					item.setProduct_sold(rs.getInt("product_sold"));
					item.setProduct_status(rs.getString("product_status"));
				}
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}
	
	public ProductObject getProductByCreator(int id) {
		ProductObject item = null;

		ResultSet rs = this.p.getProductByCreator(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(rs.getString("product_name"));
					item.setProduct_price(rs.getInt("product_price"));
					item.setProduct_original_price(rs.getInt("product_original_price"));
					item.setProduct_quantity(rs.getInt("product_quantity"));
					item.setProduct_created_by(rs.getInt("product_created_by"));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_by(rs.getInt("product_modified_by"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_pc_id(rs.getInt("product_pc_id"));
					item.setProduct_detail(rs.getString("product_detail"));
					item.setProduct_sold(rs.getInt("product_sold"));
					item.setProduct_status(rs.getString("product_status"));
				}
				 rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return item;
	}

	public ArrayList<productViewModel> getProduct(ProductObject similar, int at, byte total) {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getProduct(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setCategoryName(rs.getString("category_name"));
				item.setImageUrl(rs.getString("image_url"));
				
				item.setDiscountName(rs.getString("discount_name"));
				item.setDiscountType(rs.getString("discount_type"));
				item.setDiscountValue(rs.getInt("discount_value"));
				item.setDiscountValueType(rs.getString("discount_value_type"));
				item.setStartDate(rs.getDate ("start_date"));
				item.setEndDate(rs.getDate ("end_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public int GetTotalProducts() {
		ResultSet res = this.p.GetTotalProducts(0);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("total");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
	
	public int GetTotalProductByCategory(List<Integer> categoryIds) {
		ResultSet res = this.p.GetTotalProductByCategory(0,categoryIds);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("total");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
	
	public ArrayList<productViewModel> getProductByCategory(List<Integer> categoryIds, int at, byte total){
		ArrayList<productViewModel> list = new ArrayList<>();
		ArrayList<ResultSet> res=this.p.getProductByCategory(categoryIds,at, total);
		ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setCategoryName(rs.getString("category_name"));
				item.setImageUrl(rs.getString("image_url"));
				
				item.setDiscountName(rs.getString("discount_name"));
				item.setDiscountType(rs.getString("discount_type"));
				item.setDiscountValue(rs.getInt("discount_value"));
				item.setDiscountValueType(rs.getString("discount_value_type"));
				item.setStartDate(rs.getDate ("start_date"));
				item.setEndDate(rs.getDate ("end_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	public ArrayList<productViewModel> getProductByWord(String word, int at, byte total){
		ArrayList<productViewModel> list = new ArrayList<>();
		ArrayList<ResultSet> res=this.p.getProductByWord(word,at,total);
		ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setCategoryName(rs.getString("category_name"));
				item.setImageUrl(rs.getString("image_url"));
				
				item.setDiscountName(rs.getString("discount_name"));
				item.setDiscountType(rs.getString("discount_type"));
				item.setDiscountValue(rs.getInt("discount_value"));
				item.setDiscountValueType(rs.getString("discount_value_type"));
				item.setStartDate(rs.getDate ("start_date"));
				item.setEndDate(rs.getDate ("end_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getNewProduct(){
		ArrayList<productViewModel> list = new ArrayList<>();
		ArrayList<ResultSet> res=this.p.getNewProduct(null);
		ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setCategoryName(rs.getString("category_name"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<productViewModel> getBestSeller(){
		ArrayList<productViewModel> list = new ArrayList<>();
		ArrayList<ResultSet> res=this.p.getBestSeller(null);
		ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setCategoryName(rs.getString("category_name"));
				item.setImageUrl(rs.getString("image_url"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public int GetTotalProductByWord(String word) {
		ResultSet res = this.p.GetTotalProductByWord(0,word);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("total");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
	
	public ArrayList<productViewModel> getSaleProducts(ProductObject similar, int at, byte total) {
	    ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.getSaleProduct(similar, at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setCategoryName(rs.getString("category_name"));
				item.setImageUrl(rs.getString("image_url"));
				
				item.setDiscountName(rs.getString("discount_name"));
				item.setDiscountType(rs.getString("discount_type"));
				item.setDiscountValue(rs.getInt("discount_value"));
				item.setDiscountValueType(rs.getString("discount_value_type"));
				item.setStartDate(rs.getDate("start_date"));
				item.setEndDate(rs.getDate("end_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public productViewModel getProductDetail(int id) {
		ResultSet rs=this.p.getProductDetail(id);
		productViewModel item=null;
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new productViewModel();
		        	item.setProductId(rs.getInt("product_id"));
					item.setProductName(rs.getString("product_name"));
					item.setProductPrice(rs.getInt("product_price"));
					item.setProductSalePrice(rs.getInt("product_sale_price"));
					item.setProductDetail(rs.getString("product_detail"));
					item.setProductQuantity(rs.getInt("product_quantity"));
					item.setProductSold(rs.getInt("product_sold"));
					item.setProductStatus(rs.getString("product_status"));
					item.setCategoryName(rs.getString("category_name"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setImageUrl(rs.getString("image_url"));
					item.setProductBrand(rs.getString("product_brand"));
					
					item.setDiscountName(rs.getString("discount_name"));
					item.setDiscountType(rs.getString("discount_type"));
					item.setDiscountValue(rs.getInt("discount_value"));
					item.setDiscountValueType(rs.getString("discount_value_type"));
					item.setStartDate(rs.getDate("start_date"));
					item.setEndDate(rs.getDate("end_date"));
					item.setActive(rs.getBoolean("is_active"));
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public ArrayList<productViewModel> GetProductByBrand(List<String> brands, int at, byte total){
		ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.GetProductByBrand(brands,at, total);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setCategoryId(rs.getInt("category_id"));
				item.setCategoryName(rs.getString("category_name"));
				item.setImageUrl(rs.getString("image_url"));
				
				item.setDiscountName(rs.getString("discount_name"));
				item.setDiscountType(rs.getString("discount_type"));
				item.setDiscountValue(rs.getInt("discount_value"));
				item.setDiscountValueType(rs.getString("discount_value_type"));
				item.setStartDate(rs.getDate("start_date"));
				item.setEndDate(rs.getDate("end_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public ArrayList<String> getBrand(){
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<ResultSet> res = this.p.GetBrand(null);
		ResultSet rs = res.get(0);
		try {
	        while (rs.next()) {	
	            list.add(rs.getString("product_brand"));
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	public int GetTotalProductByBrand(List<String> brands) {
		ResultSet res = this.p.GetTotalProductByBrand(brands);
	    
	    int all = 0;
		if (res != null) {
			try {
				if (res.next()) {
					all = res.getInt("total");
				}
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return all;
	}
	
	public ArrayList<productViewModel> GetRelatedProduct(int id){
		ArrayList<productViewModel> list = new ArrayList<>();
	    ArrayList<ResultSet> res = this.p.GetRelatedProduct(id);
	    ResultSet rs = res.get(0);

	    try {
	        while (rs.next()) {
	        	productViewModel item = new productViewModel();
	        	item.setProductId(rs.getInt("product_id"));
				item.setProductName(rs.getString("product_name"));
				item.setProductPrice(rs.getInt("product_price"));
				item.setProductSalePrice(rs.getInt("product_sale_price"));
				item.setProductDetail(rs.getString("product_detail"));
				item.setProductQuantity(rs.getInt("product_quantity"));
				item.setProductSold(rs.getInt("product_sold"));
				item.setProductStatus(rs.getString("product_status"));
				item.setImageUrl(rs.getString("image_url"));
				
				item.setDiscountName(rs.getString("discount_name"));
				item.setDiscountType(rs.getString("discount_type"));
				item.setDiscountValue(rs.getInt("discount_value"));
				item.setDiscountValueType(rs.getString("discount_value_type"));
				item.setStartDate(rs.getDate("start_date"));
				item.setEndDate(rs.getDate("end_date"));
				item.setActive(rs.getBoolean("is_active"));
	            list.add(item);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
