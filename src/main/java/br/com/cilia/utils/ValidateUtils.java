package br.com.cilia.utils;

import br.com.cilia.controller.client.request.ClientRequest;
import br.com.cilia.controller.product.request.ProductRequest;
import br.com.cilia.controller.sale.request.SaleRequest;
import br.com.cilia.entity.client.Client;
import br.com.cilia.entity.product.Product;
import br.com.cilia.entity.sale.Sale;
import br.com.cilia.exception.BadRequestException;
import br.com.cilia.exception.ConflictException;
import br.com.cilia.exception.NotFoundException;

import java.util.Objects;
import java.util.Optional;

import static br.com.cilia.utils.ConstantsErrorsUtil.EXCEPTION.*;

public class ValidateUtils {

    private ValidateUtils(){

    }

    public static void clientValidate(Optional<Client> client){
        if(client.isEmpty()){
            throw new NotFoundException(MESSAGE_CLIENT_NOT_FOUND);
        }

    }

    public static void productValidate(Optional<Product> product){
        if(product.isEmpty()){
            throw new NotFoundException(MESSAGE_PRODUCT_NOT_FOUND);
        }

    }

    public static void saleValidate(Optional<Sale> sale){
        if(sale.isEmpty()){
            throw new NotFoundException(MESSAGE_SALE_NOT_FOUND);
        }

    }

    public static void clientExistsInDbValidate(String email){
        if(Objects.nonNull(email)){
            throw new ConflictException(MESSAGE_CLIENT_EXISTS);
        }

    }

    public static void productExistsInDbValidate(String email){
        if(Objects.nonNull(email)){
            throw new ConflictException(MESSAGE_PRODUCT_EXISTS);
        }

    }

    public static void clientNameNotNull(ClientRequest client){
        if(Objects.isNull(client.getName()) || client.getName().isEmpty()){
            throw new BadRequestException(MESSAGE_NAME_NOT_NULL_OR_EMPTY);
        }

    }

    public static void clientEmailNotNull(ClientRequest client){
        if(Objects.isNull(client.getEmail()) || client.getEmail().isEmpty()){
            throw new BadRequestException(MESSAGE_EMAIL_NOT_NULL_OR_EMPTY);
        }

    }

    public static void productNameNotNull(ProductRequest product){
        if(Objects.isNull(product.getName()) || product.getName().isEmpty()){
            throw new BadRequestException(MESSAGE_NAME_NOT_NULL_OR_EMPTY);
        }

    }

    public static void productPriceNotNull(ProductRequest product){
        if(Objects.isNull(product.getPrice())){
            throw new BadRequestException(MESSAGE_PRICE_NOT_NULL_OR_EMPTY);
        }

    }

    public static void saleProductListNotEmpty(SaleRequest sale){
        if(sale.getSaleProductRequests().isEmpty()){
            throw new BadRequestException(MESSAGE_SALE_PRODUCT_NOT_EMPTY);
        }
    }
}
