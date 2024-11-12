#include <virtuabotixRTC.h>
#include <Wire.h>
#include <MPU6050.h>
//PROBAJ SO NOVIOT driver od AliExpress
MPU6050 mpu;
//RTC.
virtuabotixRTC myRTC(A0, A1, A2);
double M,Y,D,MN,H,S;
double A,B;
double location =32.88;//your longtitude.
double location2 =39.933363//your latitude.
double LST_degrees;//local side real time(LST) in degrees.
double LST_hours;//local side real time(LST) in decimal hours.
unsigned long timer = 0;
float timeStep = 0.01;
double pitch = 0;
double yaw = 0;
double val = 0;//input DEC
double val2 = 0;//input RA
double temp = val2;//val2
const int stahp=7,stahp2=10;
const int cw=8,cw2=11;
const int ccw=6,ccw2=9;
void setup() {
    //sekundi, minuti, chasovi, denovi, nedela, mesec, godina
    myRTC.setDS1302Time(00, 38, 23, 5, 27, 4, 2024);
    Serial.begin(115200);
    pinMode(stahp,OUTPUT);
    pinMode(cw,OUTPUT);
    pinMode(ccw,OUTPUT);
    pinMode(stahp2,OUTPUT);
    pinMode(cw2,OUTPUT);
    pinMode(ccw2,OUTPUT);
    delay(5000);//5 sekundi pauza ili po proverka da se KOREGIRA TUKA!
    while(!mpu.begin(MPU6050_SCALE_2000DPS, MPU6050_RANGE_2G))
    {
    }
    mpu.calibrateGyro();
    mpu.setThreshold(3);
}//
void loop()
{
    //gi pretvara stepenite vo sideralni
  if(location2>0){//ako e > 0 togash se raboti za severnata hemisfera
    if( floor(LST_degrees)==LST_degrees ){ 
      if (LST_degrees>180){
        val2 = temp+(360-LST_degrees);
        }else{
        val2 = temp-LST_degrees; //voa e ako sme na juzhnata, ama ne sme xD
        }
    }
  }else{//else e za juzhna hemisfera, ama u nashiot sluchaj nema da ni treba xD.*shta juzhnije ta tuzhnije ;)
   if( floor(LST_degrees)==LST_degrees ){ 
      if (LST_degrees>180){
        val2 = temp-(360-LST_degrees);
        }else{
        val2 = temp+LST_degrees;
        }
    }
  }
    myRTC.updateTime();
    LST_time();
    recvdata();
    pitch_check();
    yaw_check();
    timer = millis();
    Vector norm = mpu.readNormalizeGyro();
    //x oska za pitch.
    //y oska za yaw.
    yaw = yaw + norm.YAxis * timeStep;
    pitch = pitch + norm.XAxis * timeStep;
    Serial.print(" Yaw = ");
    Serial.print(yaw);
    Serial.print(" Pitch = ");
    Serial.print(pitch);
    Serial.print(" LST_d = ");
    Serial.print(LST_degrees);
    Serial.print(" LST_h = ");
    Serial.println(LST_hours);//local sidereal time in decimal hours.
    delay((timeStep*1000) - (millis() - timer));//tajmer za zhiroskopot.
}
void recvdata(){
  //vaa funkcija gi prima inputite, gi parsira vo double po ,
    if (Serial.available() > 0){
        String a= Serial.readString();
        String value1, value2;
        // Ciklusot e za da gi konvertira vnesenite koordinati kako koordinata vo format so zapirka
        for (int i = 0; i < a.length(); i++) {
            if (a.substring(i, i+1) == ",") {
                value2 = a.substring(0, i);
                value1= a.substring(i+1);
                break;
            }
        }
        val=90-value1.toFloat();
        val2=value2.toFloat();
        temp = val2;
    }
}
void pitch_check(){//DA SE VIDI BUGOT SO ZAGLAVUVA NA ,14 stepeni
    //proverka za navaluvanjeto na robotot, ako ne e vo soodnos so vneseniot input, se koregira so motorite
    if(floor(pitch*100)/100==floor(val*100)/100){
        digitalWrite(stahp,HIGH);
        }else{
        digitalWrite(stahp,LOW);
    }
    if(floor(pitch*100)<floor(val*100)){
        digitalWrite(cw,HIGH);
        }else{
        digitalWrite(cw,LOW);
    }
    if(floor(pitch*100)>floor(val*100)){
        digitalWrite(ccw,HIGH);
        }else{
        digitalWrite(ccw,LOW);
    }
}
void yaw_check(){
    //ako visinata e promashena, se pravi proverkata i se isprakja poraka na nano arduinoto da se koregira
    if(floor(yaw*100)==floor(val2*100)){
        digitalWrite(stahp2,HIGH);
        }else{
        digitalWrite(stahp2,LOW);
    }
    if(floor(yaw*100)<floor(val2*100)){
        digitalWrite(cw2,HIGH);
        }else{
        digitalWrite(cw2,LOW);
    }
    if(floor(yaw*100)>floor(val2*100)){
        digitalWrite(ccw2,HIGH);
        }else{
        digitalWrite(ccw2,LOW);
    }
}
void LST_time(){
    //go presmetuva sideralnoto vreme po vaa formula podole
    //http://www.stargazing.net/kepler/altaz.html 
    M = (double) myRTC.month;
    Y = (double) myRTC.year;
    D = (double) myRTC.dayofmonth;
    MN = (double) myRTC.minutes;
    H = (double) myRTC.hours;
    S = (double) myRTC.seconds;
    A = (double)(Y-2000)*365.242199;
    B = (double)(M-1)*30.4368499;
    double JDN2000=A+B+(D-1)+myRTC.hours/24;
    double decimal_time = H+(MN/60)+(S/3600) ;
    double LST = 100.46 + 0.985647 * JDN2000 + location + 15*decimal_time;
    LST_degrees = (LST-(floor(LST/360)*360));
    LST_hours = LST_degrees/15;
}
