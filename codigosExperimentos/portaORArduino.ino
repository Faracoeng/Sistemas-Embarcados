// definir portas
// ----LED's
int REDLED = 13; //saída em alto
int YELLOWLED = 12; //saída em baixo
// ---- Chaves
int KEY01 = 11;
int KEY02 = 10;
// --- Tuts
int atraso = 500;


void setup() {
  // put your setup code here, to run once:
    pinMode(REDLED, OUTPUT);
    pinMode(YELLOWLED, OUTPUT);
    pinMode(KEY01, INPUT);
    pinMode(KEY02, INPUT);
    led_off(REDLED);
    led_off(YELLOWLED);
}

void loop() {
  // put your main code here, to run repeatedly:
    if (push_button(KEY01) == 0 and push_button(KEY02) == 0){
      led_on(REDLED); 
      led_off(YELLOWLED);
    }
    else if (push_button(KEY01) or push_button(KEY02)){
      led_off(REDLED);
      led_on(YELLOWLED);
    }
    else if (push_button(KEY01) == 1 or push_button(KEY02) == 0){
      led_off(REDLED);
      led_on(YELLOWLED);
    }
    else if (push_button(KEY01) == 1 and push_button(KEY02) == 1){
      led_off(REDLED);
      led_on(YELLOWLED);
    }

    delay(atraso);

}
void led_off(int pin){
  digitalWrite(pin,LOW);
}

void led_on(int pin){
  digitalWrite(pin,HIGH);
}

boolean push_button(int pin){
  return digitalRead(pin);
}
