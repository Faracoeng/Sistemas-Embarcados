int LED1 = 8;
int PUSH_UP = 9;
int PUSH_DOWN = 10;
int LCD_RS = 7;
int LCD_E = 6;
int LCD_D4 = 5;
int LCD_D5 = 4;
int LCD_D6 = 3;
int LCD_D7 = 2;
int atraso = 1000;
int repiq = 30;
int count;
#include <LiquidCrystal.h>

LiquidCrystal lcd(7, 6, 5, 4, 3, 2);

void setup() {
  lcd.begin(16,2);
  pinMode(LED1, OUTPUT);
  pinMode(PUSH_UP,INPUT);
  pinMode(PUSH_DOWN,INPUT);
  led_off(LED1);  
  count = 0;
}

void loop() {
  lcd.clear();

  /*led_on(LED1);
  delay(atraso);
  led_off(LED1);
  delay(atraso);*/

  lcd.setCursor(3,0);
  lcd.print("Contador");
    if(push_button(PUSH_UP)){
      count += 1;
      led_on(LED1);
      delay(repiq);
    }
  else if (push_button(PUSH_DOWN)){
      if (count > 0){
        count -= 1;
        led_off(LED1);
        delay(repiq);
      }
    }
    lcd.setCursor(3,1);
    lcd.print(count);
    delay(500);   
}
 
void led_off(int pin){
  digitalWrite(pin,LOW);
}

void led_on(int pin){
  digitalWrite(pin,HIGH);
}

boolean push_button(int pin){
 return digitalRead(pin) == HIGH;
}
