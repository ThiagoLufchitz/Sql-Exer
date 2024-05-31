# Use connect() para criar uma conexao com o servidor MySQL. Este metodo retorna um objeto MySQLConnection

import mysql.connector
from mysql.connector import errorcode
import datetime

# tentando estabelecer a conexao com o servidor mysql
con = None
try:
    con = mysql.connector.connect(user='linder', password='12345', host='127.0.0.1', database='ExLabBD')
    print("Conexão estabelecda!")
except mysql.connector.Error as err:
    if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
        print("Usuario ou Password incorretos")
    elif err.errno == errorcode.Er_BAD_DB_ERROR:
        print("Banco de dados ExLabBd nao existe")
    else:
        print(err)


# Inicialmente recuperamos um objeto cursor da conexao
cur = con.cursor()


# vamos fazer alguns inserts
add_funcionario = "INSERT INTO FUNCIONARIO (DataNasc, Pnome, UNome, Sexo, DataAdm) VALUES (%s, %s, %s, %s, %s)"
dados_funcionario = (datetime.date(2010, 10, 14), 'Linder', 'Silva', 'M', datetime.date(2021, 1, 15))
cur.execute(add_funcionario, dados_funcionario)
print('Funcionario inserido!')


add_salario = ("INSERT INTO SALARIOS (func_no, valor, dataInic, dataFim) VALUES (%s, %s, %s, %s)")
empNo = cur.lastrowid #recupera o numero do funcionario
dados_salario = (empNo, 1000, datetime.date(2021, 1, 15), datetime.date(2022, 1, 15))
cur.execute(add_salario, dados_salario)
print('Salario inserido!')

#faz o commit e fecha
con.commit()
cur.close()
con.close()





