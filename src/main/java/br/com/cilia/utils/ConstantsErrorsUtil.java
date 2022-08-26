package br.com.cilia.utils;

public interface ConstantsErrorsUtil {

    interface EXCEPTION {
        String MESSAGE_SALE_NOT_FOUND = "Venda não encontrado";
        String MESSAGE_NOT_FOUND_CLIENT_SALE = "Cliente associado a essa venda não existe";
        String MESSAGE_CLIENT_NOT_FOUND = "Cliente não encontrado";
        String MESSAGE_PRODUCT_NOT_FOUND = "Produto não encontrado";
        String MESSAGE_CLIENT_EXISTS = "Cliente já cadastrado com esse email.";
        String MESSAGE_PRODUCT_EXISTS = "Produto já cadastrado com esse nome.";
        String MESSAGE_NAME_NOT_NULL_OR_EMPTY = "Obrigatorio informar o nome.";
        String MESSAGE_EMAIL_NOT_NULL_OR_EMPTY = "Obrigatorio informar o email.";
        String MESSAGE_PRICE_NOT_NULL_OR_EMPTY = "Obrigatorio informar o preço.";
        String MESSAGE_SALE_PRODUCT_NOT_EMPTY = "Obrigatorio adicionar pelomenos um produto.";
    }
}
