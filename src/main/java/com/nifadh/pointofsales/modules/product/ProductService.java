package com.nifadh.pointofsales.modules.product;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import com.nifadh.pointofsales.modules.commondtos.SoftDeleteRequest;
import com.nifadh.pointofsales.modules.product.brand.Brand;
import com.nifadh.pointofsales.modules.product.brand.BrandService;
import com.nifadh.pointofsales.modules.product.category.Category;
import com.nifadh.pointofsales.modules.product.category.CategoryService;
import com.nifadh.pointofsales.modules.product.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final StockService stockService;


    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryService categoryService, BrandService brandService, @Lazy StockService stockService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.stockService = stockService;
    }

    @Transactional
    public ProductResponse addProduct(ProductRequest productRequest) {
        checkForDuplicateProduct(productRequest.getName());
        Category category = categoryService.findCategoryById(productRequest.getCategoryId());
        Brand brand = brandService.findBrandById(productRequest.getBrandId());

        Product product = productMapper.productRequestToProduct(productRequest, category, brand);
        String itemCode = generateItemCode(category.getName(), brand.getName());
        product.setItemCode(itemCode);
        product.setIsDeleted(false);
        product = productRepository.save(product);

        stockService.createStockForNewProduct(product);
        return productMapper.productToProductResponse(product);
    }

    public String generateItemCode(String categoryName, String brandName) {
        String categoryPrefix = categoryName.substring(0, 2).toUpperCase();
        String brandPrefix = brandName.substring(0, 2).toUpperCase();
        String itemCode;
        do {
            int randomValue = new Random().nextInt(9000) + 1000;
            itemCode = categoryPrefix + "-" + brandPrefix + "-" + randomValue;
        } while (productRepository.existsByItemCode(itemCode));
        return itemCode;
    }

    public Product findProductById(Integer productId) {
        checkIfProductIdIsValid(productId);
        return productRepository.findById(productId).get();
    }

    public ProductResponseList getAllProducts() {
        List<Product> products = productRepository.findByIsDeletedIsFalse();
        return productMapper.productListToProductResponseList(products);
    }


    public void changeSoftDeleteStatus(Integer productId, SoftDeleteRequest softDeleteRequest) {
        checkIfProductIdIsValid(productId);
        Product product = productRepository.findById(productId).orElseThrow();
        product.setIsDeleted(softDeleteRequest.getIsDeleted());
        productRepository.save(product);
    }


    public ProductResponse editProductById(Integer productId, ProductRequest productRequest) {
        checkIfProductIdIsValid(productId);
        Category category = categoryService.findCategoryById(productRequest.getCategoryId());
        Brand brand = brandService.findBrandById(productRequest.getBrandId());
        Product product = productMapper.productRequestToProduct(productRequest, category, brand);
        product.setId(productId);
        return productMapper.productToProductResponse(productRepository.save(product));
    }


    public void hardDeleteProduct(Integer productId) {
        checkIfProductIdIsValid(productId);
        productRepository.deleteById(productId);
    }




    //validations
    private void checkForDuplicateProduct(String name) {
        if (productRepository.existsByNameEqualsIgnoreCase(name)) {
            throw new DuplicateResourceException(
                    "Product: '" + name + "' already exists!"
            );
        }
    }

    private void checkIfProductIdIsValid(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    "Product with ID: " + productId + " does not exist!"
            );
        }
    }





}
