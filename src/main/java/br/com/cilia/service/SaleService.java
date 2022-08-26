package br.com.cilia.service;

import br.com.cilia.controller.sale.request.SaleRequest;
import br.com.cilia.controller.sale.response.SaleResponse;
import br.com.cilia.converter.ClientConverter;
import br.com.cilia.converter.SaleConverter;
import br.com.cilia.exception.NotFoundException;
import br.com.cilia.repository.client.ClientRepository;
import br.com.cilia.repository.sale.SaleRepository;
import br.com.cilia.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static br.com.cilia.utils.ConstantsErrorsUtil.EXCEPTION.MESSAGE_NOT_FOUND_CLIENT_SALE;
import static br.com.cilia.utils.ConstantsErrorsUtil.EXCEPTION.MESSAGE_SALE_NOT_FOUND;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    public SaleResponse saveSale(SaleRequest saleRequest){

        validateFields(saleRequest);
        var sale = SaleConverter.toEntity(saleRequest);
        return SaleConverter.toResponse(saleRepository.save(sale));
    }

    public Page<SaleResponse> findAllSale(Pageable pageable){
        var sale = saleRepository.findAll(pageable);
        return SaleConverter.toPageSaleResponse(sale);
    }

    public SaleResponse findSale(Long id){
        System.out.println(LocalDate.now());
        var sale = saleRepository.findById(id);
        ValidateUtils.saleValidate(sale);
        return SaleConverter.toResponse(sale.get());
    }

    public SaleResponse updateSale(SaleRequest saleRequest, Long id) {

        var sale = saleRepository.findById(id).map(sal -> {
            sal.setClient(ClientConverter.toSaleClientEntity(saleRequest.getClientRequest()));
            sal.setProducts(SaleConverter.toListProduct(saleRequest.getSaleProductRequests()));
            return saleRepository.save(sal);
        });
        ValidateUtils.saleValidate(sale);
        return SaleConverter.toResponse(sale.get());
    }

    public SaleResponse deleteSale(Long id){

        if(!saleRepository.existsById(id)) {
            throw new NotFoundException(MESSAGE_SALE_NOT_FOUND);
        }
        saleRepository.deleteById(id);
        return null;
    }

    public void validateFields(SaleRequest saleRequest){
        if(!clientRepository.existsById(saleRequest.getClientRequest().getId())){
            throw new NotFoundException(MESSAGE_NOT_FOUND_CLIENT_SALE);
        }
        ValidateUtils.saleProductListNotEmpty(saleRequest);
    }
}
