 DELIMITER $$

create TRIGGER atualiza_estoque after insert on Venda
BEGIN
    for each row 
        update Produto
        set quantidade_estoque  = quantidade_estoque - new.quantidade_estoque
        where new.idP = idP; 
END$$

DELIMITER;

