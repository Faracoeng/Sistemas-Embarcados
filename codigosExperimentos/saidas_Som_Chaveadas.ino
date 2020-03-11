int freq_S1 = 400;
int freq_S2 = 1000;
int KEY_01 = 10;
int KEY_02 = 9;
int saida = 12;


void setup() {
    pinMode(saida, OUTPUT);
    pinMode(KEY_01, INPUT);
    pinMode(KEY_02, INPUT);

}

void loop() {
  if (push_button(KEY_01)){
    tone(saida,freq_S1);
    delay(2);
  }
  else if (push_button(KEY_02)){
    tone(saida,freq_S2);
    delay(2);
  }
}
boolean push_button(int pin){
  return digitalRead(pin);
}
