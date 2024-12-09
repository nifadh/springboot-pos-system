package com.nifadh.pointofsales.modules.product.stock;

import com.nifadh.pointofsales.modules.branch.Branch;
import com.nifadh.pointofsales.modules.branch.BranchService;
import com.nifadh.pointofsales.modules.product.Product;
import com.nifadh.pointofsales.modules.product.ProductService;
import com.nifadh.pointofsales.modules.product.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final BranchService branchService;
    private final ProductService productService;


    @Autowired
    public StockService(StockRepository stockRepository, BranchService branchService, @Lazy ProductService productService) {
        this.stockRepository = stockRepository;
        this.branchService = branchService;
        this.productService = productService;
    }

    @Transactional
    public void createStockForNewProduct(Product product) {
        List<Branch> branches = branchService.getBranchList();
        for (Branch branch : branches) {
            Stock.StockBuilder stockBuilder = Stock.builder()
                    .product(product)
                    .branch(branch);

            if (product.getProductType() == ProductType.INDIVIDUAL) {
                stockBuilder.quantity(0).weight(null);
            } else if (product.getProductType() == ProductType.WEIGHTED) {
                stockBuilder.quantity(null).weight(0.0);
            }

            Stock stock = stockBuilder.build();
            stockRepository.save(stock);
        }
    }

    public void updateStock(Integer productId, StockUpdateRequest stockUpdateRequest) {
        Product product = productService.findProductById(productId);
        Branch branch = branchService.findBranchById(stockUpdateRequest.getBranchId());

        Stock stock = stockRepository.findByProductAndBranch(product, branch).orElseThrow();

        if (product.getProductType() == ProductType.INDIVIDUAL) {
            stock.setQuantity(stockUpdateRequest.getQuantity());
        } else if (product.getProductType() == ProductType.WEIGHTED) {
            stock.setWeight(stockUpdateRequest.getWeight());
        }
        stockRepository.save(stock);
    }
}
