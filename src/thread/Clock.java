package thread;

import java.util.Calendar;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Clock extends Thread {
	
	private Label time; 
	private Calendar calendar;
	
    public Clock(Label time) { 
        this.time = time;
    }
    
    @Override
    public void run() {
    	while(true) {
            Platform.runLater(()->{
            	updateClock();
            }
            
            );
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    	}
    	
    }
    
    //Method to update the label where is showed the time to the user----------------------------------------------------
    public void updateClock() {
    	calendar = Calendar.getInstance(); //this line brings the system time
    	
    	int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
    	
    	String amPM = calendar.get(Calendar.AM_PM) == Calendar.AM?"AM":"PM";
    	
    	if (amPM.equals("PM")) {
			if (hourOfDay != 12) {
				hourOfDay = hourOfDay - 12;
			}
		}
  	
        if (calendar.get(Calendar.MINUTE) < 10 && calendar.get(Calendar.SECOND) < 10) {
            time.setText(String.valueOf(hourOfDay + ":0" + calendar.get(Calendar.MINUTE) + ":0" + calendar.get(Calendar.SECOND)) + " " + amPM);
        } 
        else if (calendar.get(Calendar.MINUTE) < 10) {
            time.setText(String.valueOf(hourOfDay + ":0" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)) + " " + amPM);
        } 
        else if (calendar.get(Calendar.SECOND) < 10) {
            time.setText(String.valueOf(hourOfDay + ":" + calendar.get(Calendar.MINUTE) + ":0" + calendar.get(Calendar.SECOND)) + " " + amPM);
        } 
        else {
           time.setText(String.valueOf(hourOfDay + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)) + " " + amPM);
        }
        
    }
    //---------------------------------------------------------------------------------------------------------------------------
}

