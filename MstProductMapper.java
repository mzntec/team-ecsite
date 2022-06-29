package jp.co.internous.brown.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.internous.brown.model.domain.MstProduct;

@Mapper
public interface MstProductMapper{

	List<MstProduct> findAll();
	
	List<MstProduct> findByCategoryId(@Param("categoryId")int categoryId);
	
	List<MstProduct> findByItemName(@Param("keywords") String[] keywords);
	
	List<MstProduct> findByCategoryIdAndItemName(@Param("categoryId") int categoryId,@Param("keywords")String[] keywords);
	
	List<MstProduct> findByProductId(@Param("productId")int productId);
		
}
