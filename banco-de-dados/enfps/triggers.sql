
------------------------------------------------------TRIGGER 1------------------------------------------------------
--TRIGGER PARA ATUALIZAR A QUANTIDADE NO ESTOQUE
--Declaração da função estoque
CREATE FUNCTION estoque()
RETURNS TRIGGER AS
'
	--Declarar uma variavel (quantidade_atual) para armazenar a quantidade atual
	DECLARE quantidade_atual NUMERIC;

	BEGIN
		--Buscar a quantidade atual
		SELECT INTO quantidade_atual qntd_estoque FROM Medicamento WHERE id_medicamento = NEW.id_medicamento;

		--Caso a quantidade nova for zero, avisar que a quantidade não pode ser nulo 
		IF NEW.quantidade = 0 THEN
            		RAISE EXCEPTION ''A quantidade não pode ser nulo'';
		
		--Caso a quantidade atual for menor do que a quantidade nova, retorne um aviso
		ELSIF quantidade_atual < NEW.quantidade THEN
			RAISE EXCEPTION ''Estoque abaixo da quantidade solicitada'';	
		
		--Caso contrário, atualizar o estoque
		ELSE
			UPDATE medicamento SET qntd_estoque = quantidade_atual - NEW.quantidade
				WHERE id_medicamento = NEW.id_medicamento;
			RAISE NOTICE ''Estoque atualizado com sucesso'';
		END IF;
		RETURN NEW;	  		
	END;
'
LANGUAGE 'plpgsql';

--Declarção do trigger tg_estoque na tabela prescricao_medicamento
CREATE TRIGGER tg_estoque
BEFORE INSERT OR UPDATE ON prescricao_medicamento FOR EACH ROW
EXECUTE PROCEDURE estoque();



------------------------------------------------------TRIGGER 2------------------------------------------------------
--TRIGGER QUE MUDA O ESTADO DE OCUPAÇÃO DO LEITO
--Declaração da função ocupacao_leito
CREATE FUNCTION ocupacao_leito() 
RETURNS OPAQUE AS
'
	BEGIN
        --If para ver se o novo dado eh nulo
		IF NEW.data_saida IS null THEN

        --Caso for nulo ele exibe uma mensagem
        	RAISE EXCEPTION ''A data_saida não pode ser nulo'';

        --Se nao for nulo ele muda o campo estado de ocupacao para vazio
		ELSE
			UPDATE leito SET estado_ocupacao = ''VAZIO''
				WHERE id_leito = NEW.id_leito;
			RAISE NOTICE ''Leito não está mais ocupado'';

        --Fim do if
		END IF;
		RETURN NEW;	  		
	END;
'

LANGUAGE 'plpgsql';	

--Declarção do trigger tg_ocupacao_leito na tabela internacao
CREATE TRIGGER tg_ocupacao_leito
AFTER UPDATE OR INSERT ON internacao FOR EACH ROW
EXECUTE PROCEDURE ocupacao_leito();



------------------------------------------------------TRIGGER 3------------------------------------------------------

--ALTER TABLE Paciente ADD Agenda_procedimento DATE;

--TRIGGER QUE MUDA O ESTADO DE OCUPAÇÃO DO LEITO
--Declaração da função agenda_procedimento
CREATE FUNCTION agenda_procedimento() 
RETURNS OPAQUE AS
'
	BEGIN
        --If para verificar se o novo dado da data de inicio eh nulo
		IF NEW.data_inicio IS null THEN

        --Se for nulo ele exibe uma mensagem e para a execucao 
        	RAISE EXCEPTION ''Não possui data inicio'';
		
        --If para verificar se o novo dado da data de termino nao eh nulo
		ELSIF NEW.data_termino IS NOT null THEN 

        --Se nao for nulo ele exibe uma mensagem e para a execucao
		RAISE EXCEPTION ''Paciente já concluiu o procedimento'';
		
        --Else para verificar se nenhuma das condicoes acima for verdadeira, entao
		ELSE

        --Cria uma nova data na agenda
			UPDATE Paciente SET Agenda_procedimento = NEW.data_inicio
				WHERE id_paciente = NEW.id_paciente;
			RAISE NOTICE ''Data marcada na agenda do paciente'';

        --Fim do if
		END IF;
		RETURN NEW;	  		
	END;
'
LANGUAGE 'plpgsql';	

--Declarção do trigger tg_agenda_procedimento na tabela Procedimento
CREATE TRIGGER tg_agenda_procedimento
AFTER INSERT ON Procedimento FOR EACH ROW
EXECUTE PROCEDURE agenda_procedimento();


--Exemplo de teste
INSERT INTO procedimento (seq_procedimento, data_termino, observacao, horario, data_inicio, periodicidade, id_paciente, id_tipo_procedimento)
VALUES ( 10, NULL, 'NULL', '23:34:45', '2019-11-20', '1', 2, 3);

UPDATE Procedimento SET data_inicio = '2019-11-01'
WHERE id_paciente = 5;
