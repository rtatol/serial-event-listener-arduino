
void setup() {                
  Serial.begin(9600);  
}

void loop() {
  Serial.println("Hello world");
  
  String input = readSerialString();
  Serial.println(input);

  delay(1000);
}

String readSerialString() {
  String content = "";
  char character;

  while(Serial.available()) {
    character = Serial.read();
    content.concat(character);
  }

  return content;
}

