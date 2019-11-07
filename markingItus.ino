// This #include statement was automatically added by the Particle IDE.
#include <InternetButton.h>

InternetButton button = InternetButton();
int DELAY = 200;
void setup() {
 button.begin();
Particle.function("hppy0",Happy0);
Particle.function("hppy",Happy);
Particle.function("hppy1",Happy1);
Particle.function("hppy2",Happy2);
Particle.function("hppy3",Happy3);
Particle.function("Angry",Angry);

    for(int i=0;i<3;i++){
    button.allLedsOn(0,20,0);
    delay(500);
    button.allLedsOff();   
    delay(500);
    }
}


int Happy0(String cmd){
      button.allLedsOff();

    for(int i = 3; i <= 9; i++){
        button.ledOn(i,0,255,0);
        button.ledOn(1,0,255,0);
        button.ledOn(11,0,255,0);
    }
    
}
int Happy(String cmd){
    button.allLedsOff();

    for(int i = 4; i <= 8; i++){
        button.ledOn(i,0,255,0);
        button.ledOn(1,0,255,0);
        button.ledOn(11,0,255,0);
    }
}


int Happy1(String cmd){
      button.allLedsOff();

    for(int i = 5; i <= 7; i++){
        button.ledOn(i,0,255,0);
        button.ledOn(1,0,255,0);
        button.ledOn(11,0,255,0);
    }
    
}

int Happy2(String cmd){
      button.allLedsOff();
        button.ledOn(6,0,255,0);
        button.ledOn(1,0,255,0);
        button.ledOn(11,0,255,0);
}
int Happy3(String cmd){
      button.allLedsOff();
        // button.ledOn(6,0,255,0);
        button.ledOn(1,0,255,0);
        button.ledOn(11,0,255,0);
}


int Angry(String cmd){
      button.allLedsOff();
        // button.ledOn(6,0,255,0);
        for(int i = 5; i <= 7; i++){
        button.ledOn(i,255,0,0);
        button.ledOn(1,255,0,0);
        button.ledOn(11,255,0,0);
    }
        
}




void loop() {
  
  
//   if(button.buttonOn(4)){
//       // choice A
//       Particle.publish("playerChoice","A",60,PRIVATE);
//       delay(DELAY);
//   }
//   if(button.buttonOn(2)){
//       //choice B
//       Particle.publish("playerChoice","B",60,PRIVATE);
//       delay(DELAY);
//   }
//   if(button.buttonOn(3)){
//       //Next question
//       Particle.publish("playerChoice","true",60,PRIVATE);
//       delay(DELAY);
//   }
//   if(button.buttonOn(1)){
      
//       Particle.publish("playerChoice","C",60,PRIVATE);
//       delay(DELAY);
//   }


}
