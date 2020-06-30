int ledPin =  13; //atribui o pino 13 a variável ledPin 
int dado; //variável que receberá os dados da porta serial
 
void setup(){
  Serial.begin(9600);//frequência da porta serial
   pinMode(ledPin,OUTPUT); //define o pino o ledPin como saída
}
 
void loop(){
  if(Serial.available() > 0){ //verifica se existe comunicação com a porta serial
      dado = Serial.read();//lê os dados da porta serial
      switch(dado){
        case 1:
           digitalWrite(ledPin,HIGH); //liga o pino ledPin
        break;
        case 2:
           digitalWrite(ledPin,LOW); //desliga o pino ledPin
         break;
    }
  }
}
