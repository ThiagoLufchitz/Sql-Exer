# Use connect() para criar uma conexao com o servidor MySQL. Este metodo retorna um objeto MySQLConnection

import mysql.connector
from mysql.connector import errorcode
import datetime

# tentando estabelecer a conexao com o servidor mysql
try:
    con = mysql.connector.connect(user='linder', password='12345', host='127.0.0.1', database='ExLabBD')
    print('conexao estabeleciada!')
except mysql.connector.Error as err:
    if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
        print("Usuario ou Password incorretos")
    elif err.errno == errorcode.Er_BAD_DB_ERROR:
        print("Banco de dados ExLabBD nao existe")
    else:
        print(err)

# Para fazer uma consulta recuperamos um objeto cursor da conexao
cur = con.cursor()

#Monta a consulta
consulta =  ("SELECT Pnome, UNome, DataAdm, Valor FROM FUNCIONARIO, SALARIOS "
            "WHERE FUNCIONARIO.func_no = SALARIOS.func_no AND dataADM BETWEEN %s AND %s")
inicio = datetime.date(2021,1,1)
fim = datetime.date(2021,12,31)
cur.execute(consulta, (inicio, fim))

print('Consulta realizada, seguem as tuplas recuperadas:')
for (Pnome, UNome, DataAdm, Valor) in cur:
    print("{}, {} admitido em {:%d %b %Y} recebe {}".format(Pnome, UNome, DataAdm, Valor))

#fecha a conexao
cur.close()
con.close()

