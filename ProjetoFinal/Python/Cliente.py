# utilizado para estabelecer as conexoes
import socket
# API que possibilita acessar GPIO da Rapberry
import RPi.GPIO as gpio
#import Adafruit_DTH
# para contar os tempos dos LED's
import time

def estabelecerConexao():
    rasp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP) # UDP

    # Habilita o modo braodcast
    rasp.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

    #Porta alocada para o recebimento do broadcast
    rasp.bind(("", 1234))

    print("aguardando mensagem broadcast")
                
    data, address = rasp.recvfrom(1024)
    print("Mensagem recebida: " + address[0])
    # address[0] contém o IP do servidor

    # ----------------- estabelecer conexao TCP -----------------
    ipServidor = address[0]
    porta = 4321
    # criando socket TCP
    conexao = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    conexao.connect((ipServidor,porta))
    print("conexao estabelecida")
    
    # recebendo mensagem do servidor
    mensagem = conexao.recv(1024).decode('UTF-8')
    print(mensagem)
   #  resposta = "olausuario"
   # conexao.send(resposta.encode('utf-8'))
    conexao.sendall(menu().encode('UTF-8'))
    print("passou")
    respostaUsuario = conexao.recv(1024).decode('UTF-8')
    return respostaUsuario 

def menu():
    return '\n1 - Ler sensor de temperatura e umidade;\n' \
           '3 - Acender ler;\n' \
           '4 - Apagar led.\n'
def habilitarGPIO():
            gpio.setmode(gpio.BOARD)
            gpio.setup(11,gpio.OUT)
            
def opcoes(i):
    switcher = {
        # ler sensor 
        1: {
            leituraSensor()
        },
        # acender led
        2: {
            habilitarGPIO()
            # acende LED
            #gpio.output(11,gpio.HIGH)
            #time.sleep(2)
            # reseta configuraçoes
            #gpio.cleanup()
        },
        # apagar led
        3: {
            habilitarGPIO()
            # gpio.output(11,gpio.LOW)
            # time.sleep(2)
            # reseta configuraçoes
            # gpio.cleanup()
        },
        4:'Saindo'
    }
    return switcher.get(i, "Invalid option")

def leituraSensor():
    # Define o tipo de sensor
    sensor = Adafruit_DHT.DHT11
    #sensor = Adafruit_DHT.DHT22
       
    GPIO.setmode(GPIO.BOARD)
             
    # Define a GPIO conectada ao pino de dados do sensor
    pino_sensor = 25
         
    # Informacoes iniciais
    print ("*** Lendo os valores de temperatura e umidade");
       
    while True:           
        # Efetua a leitura do sensor
        umid, temp = Adafruit_DHT.read_retry(sensor, pino_sensor);
        # Caso leitura esteja ok, mostra os valores na tela
        if umid is not None and temp is not None:
            print ("Temperatura = {0:0.1f}  Umidade = {1:0.1f}n").format(temp, umid);
            print ("Aguarda 5 segundos para efetuar nova leitura...n");
            time.sleep(5)
        else:
            # Mensagem de erro de comunicacao com o sensor
            print("Falha ao ler dados do DHT11 !!!")
                 
resposta = estabelecerConexao()

while  resposta != '4':
    opcoes(int(respostaUsuario))
    
    
