import mysql.connector
from mysql.connector import errorcode

try:
    con = mysql.connector.connect(user='thiago', password='12345', host='127.0.0.1', database='Q1')
    print('conexao estabeleciada!')
except mysql.connector.Error as err:
    if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
        print("Usuario ou Password incorretos")
    elif err.errno == errorcode.Er_BAD_DB_ERROR:
        print("Banco de dados nao existe")
    else:
        print(err)

cur = con.cursor()
consulta =  ("CALL Verificar_Q(%s);")
variavel = input()
cur.execute(consulta,variavel )

print('Consulta realizada:')
for (Valor) in cur:
    print("Media e {}".format(Valor))
cur.close()
con.close()

