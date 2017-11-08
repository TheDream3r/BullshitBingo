package com.teddybeer.bullshitbingo;

import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.String;

public class GameScreen extends AppCompatActivity {

    //Hier die Rows, gebaut aus framenten (init, siehe onCreate());
    private RowFragment row1;
    private RowFragment row2;
    private RowFragment row3;
    private RowFragment row4;
    private RowFragment row5;

    private ArrayList<String> spurchListe; //Liste mit Sprüchen...
    private Boolean buttons[][]; //Für die True/False Werte der Buttons
    private Toast toast; //Zum anzeigen des Bingos
    private boolean fertig; //Wenn einmal Bingo erreicht wurde, wird das auf true gesetzt...

    private SoundPool sound = null; //SoundPool für ausgabe
    private final static int AUSGABE = AudioManager.STREAM_MUSIC;
    private int mySound = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        spurchListe = new ArrayList<String>();



        row1 = (RowFragment)getFragmentManager().findFragmentById(R.id.fragment1);
        row2 = (RowFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        row3 = (RowFragment)getFragmentManager().findFragmentById(R.id.fragment3);
        row4 = (RowFragment)getFragmentManager().findFragmentById(R.id.fragment4);
        row5 = (RowFragment)getFragmentManager().findFragmentById(R.id.fragment5);
        buttons  = new Boolean[5][5];
        //row1.setTg1Text("Hello");
        //row3.setTg3Text("Abc Ded ddkak kadsakk ksaksak dadda");




    }

    @Override
    public void onResume(){
        super.onResume();

        sound = new SoundPool(15,AUSGABE,0);

        setRowTexts();
        fertig = false;
        getSound();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    @Override
    public void onPause(){
        super.onPause();
        sound.unload(mySound);
        sound.release();
        sound = null;

    }


    private void setRowTexts(){

        //Einlesen der Sprüche und speichern in eine Liste
        AssetManager manager = getAssets();
        try
        {
            InputStream is = manager.open("txt/spruche.txt");
            Scanner s = new Scanner(is, "UTF8").useDelimiter("\\x04"); //Liefert die Gesamte Datei zurück
            String ganz = s.hasNext() ? s.next() : ""; //Speichert alles bis zum leeren String in den String "ganz"
            String arraySpruch[] = ganz.split("\n"); //"ganz" wird jetzt mit jedem "\n" in dem Array aufgesplitet

            for(String string : arraySpruch) { //added der Liste die einzelenen Sprüche zu...
                spurchListe.add(string);
            }
            //Ist glaub ich doch sehr umständlich... GEGE


        }
        catch(IOException e){

        }

        //Wenn was in der Liste steht hier rein... (Falls einlesen nicht geklappt hat...)
        if(spurchListe.size() !=0){

            Collections.shuffle(spurchListe); //druchmischen der Liste

            if(spurchListe.size() >= 25) {
                row1.setTg1Text(spurchListe.get(0));
                row1.setTg2Text(spurchListe.get(1));
                row1.setTg3Text(spurchListe.get(2));
                row1.setTg4Text(spurchListe.get(3));
                row1.setTg5Text(spurchListe.get(4));
                row2.setTg1Text(spurchListe.get(5));
                row2.setTg2Text(spurchListe.get(6));
                row2.setTg3Text(spurchListe.get(7));
                row2.setTg4Text(spurchListe.get(8));
                row2.setTg5Text(spurchListe.get(9));
                row3.setTg1Text(spurchListe.get(10));
                row3.setTg2Text(spurchListe.get(11));
                row3.setTg3Text(spurchListe.get(12));
                row3.setTg4Text(spurchListe.get(13));
                row3.setTg5Text(spurchListe.get(14));
                row4.setTg1Text(spurchListe.get(15));
                row4.setTg2Text(spurchListe.get(16));
                row4.setTg3Text(spurchListe.get(17));
                row4.setTg4Text(spurchListe.get(18));
                row4.setTg5Text(spurchListe.get(19));
                row5.setTg1Text(spurchListe.get(20));
                row5.setTg2Text(spurchListe.get(21));
                row5.setTg3Text(spurchListe.get(22));
                row5.setTg4Text(spurchListe.get(23));
                row5.setTg5Text(spurchListe.get(24));

            }

        }

    }




    //Einbinden des Row-Fragments und ansprechen der Buttons
    public static class RowFragment extends Fragment {

        private ToggleButton tg1;
        private ToggleButton tg2;
        private ToggleButton tg3;
        private ToggleButton tg4;
        private ToggleButton tg5;

        @Override
        public void onCreate(Bundle b){
            super.onCreate(b);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved){

            View view = inflater.inflate(R.layout.five_button_row_fragment, container, false);

            tg1 = (ToggleButton)view.findViewById(R.id.toggleButton1);
            tg2 = (ToggleButton)view.findViewById(R.id.toggleButton2);
            tg3 = (ToggleButton)view.findViewById(R.id.toggleButton3);
            tg4 = (ToggleButton)view.findViewById(R.id.toggleButton4);
            tg5 = (ToggleButton)view.findViewById(R.id.toggleButton5);

            return view;
        }

        /*Setzen der On- und Off-Texte der Buttons Tg1-Tg5*/
        public void setTg1Text(String s){
            tg1.setTextOn(s);
            tg1.setTextOff(s);
            tg1.setChecked(true);
            tg1.setChecked(false);
        }

        public void setTg2Text(String s){
            tg2.setTextOn(s);
            tg2.setTextOff(s);
            tg2.setChecked(true);
            tg2.setChecked(false);
        }

        public void setTg3Text(String s){
            tg3.setTextOn(s);
            tg3.setTextOff(s);
            tg3.setChecked(true);
            tg3.setChecked(false);
        }

        public void setTg4Text(String s){
            tg4.setTextOn(s);
            tg4.setTextOff(s);
            tg4.setChecked(true);
            tg4.setChecked(false);
        }

        public void setTg5Text(String s){
            tg5.setTextOn(s);
            tg5.setTextOff(s);
            tg5.setChecked(true);
            tg5.setChecked(false);
        }
        /**************************************************/
    }

    private void getButtonStatus(){ //Funktion, die die Status der Buttons in das Array schreibt --> für die überprüfung
        buttons[0][0] = row1.tg1.isChecked();
        buttons[0][1] = row1.tg2.isChecked();
        buttons[0][2] = row1.tg3.isChecked();
        buttons[0][3] = row1.tg4.isChecked();
        buttons[0][4] = row1.tg5.isChecked();
        buttons[1][0] = row2.tg1.isChecked();
        buttons[1][1] = row2.tg2.isChecked();
        buttons[1][2] = row2.tg3.isChecked();
        buttons[1][3] = row2.tg4.isChecked();
        buttons[1][4] = row2.tg5.isChecked();
        buttons[2][0] = row3.tg1.isChecked();
        buttons[2][1] = row3.tg2.isChecked();
        buttons[2][2] = row3.tg3.isChecked();
        buttons[2][3] = row3.tg4.isChecked();
        buttons[2][4] = row3.tg5.isChecked();
        buttons[3][0] = row4.tg1.isChecked();
        buttons[3][1] = row4.tg2.isChecked();
        buttons[3][2] = row4.tg3.isChecked();
        buttons[3][3] = row4.tg4.isChecked();
        buttons[3][4] = row4.tg5.isChecked();
        buttons[4][0] = row5.tg1.isChecked();
        buttons[4][1] = row5.tg2.isChecked();
        buttons[4][2] = row5.tg3.isChecked();
        buttons[4][3] = row5.tg4.isChecked();
        buttons[4][4] = row5.tg5.isChecked();
    }

    private boolean checkIfROWBingo(){ //checkt, ob in einer Zeile Bingo erziehlt wurde...
        boolean back = false;
        if (buttons[0][0] && buttons[0][1] && buttons[0][2] && buttons[0][3] && buttons[0][4]){
            back = true;
        }
        else if (buttons[1][0] && buttons[1][1] && buttons[1][2] && buttons[1][3] && buttons[1][4]){
            back = true;
        }
        else if (buttons[2][0] && buttons[2][1] && buttons[2][2] && buttons[2][3] && buttons[2][4]){
            back = true;
        }
        else if (buttons[3][0] && buttons[3][1] && buttons[3][2] && buttons[3][3] && buttons[3][4]){
            back = true;
        }
        else if (buttons[4][0] && buttons[4][1] && buttons[4][2] && buttons[4][3] && buttons[4][4]){
            back = true;
        }
        return back;
    }

    private boolean checkIfCOLUMNBingo(){ //checkt, ob in einer Spalte Bingo erziehlt wurde...
        boolean back = false;
        if (buttons[0][0] && buttons[1][0] && buttons[2][0] && buttons[3][0] && buttons[4][0]){
            back = true;
        }
        else if (buttons[0][1] && buttons[1][1] && buttons[2][1] && buttons[3][1] && buttons[4][1]){
            back = true;
        }
        else if (buttons[0][2] && buttons[1][2] && buttons[2][2] && buttons[3][2] && buttons[4][2]){
            back = true;
        }
        else if (buttons[0][3] && buttons[1][3] && buttons[2][3] && buttons[3][3] && buttons[4][3]){
            back = true;
        }
        else if (buttons[0][4] && buttons[1][4] && buttons[2][4] && buttons[3][4] && buttons[4][4]){
            back = true;
        }
        return back;
    }

    private boolean checkIfDIAGBingo(){ //checkt, ob in einer Diagonale Bingo erziehlt wurde...
        boolean back = false;
        if (buttons[0][0] && buttons[1][1] && buttons[2][2] && buttons[3][3] && buttons[4][4]){
            back = true;
        }
        else if (buttons[0][4] && buttons[1][3] && buttons[2][2] && buttons[3][1] && buttons[4][0]){
            back = true;
        }
        return back;
    }

    private boolean checkIfBingo(){
        boolean back  = false;
        if(checkIfCOLUMNBingo()){ back = true;}
        if(checkIfDIAGBingo()){back = true;}
        if(checkIfROWBingo()){back = true;}
        return back;
    }



    public void onAktionClick(View v){ //Listerner ist in der XML für jeden Button eingestellt! (Siehe Fragment)

        toast = Toast.makeText(this,"BINGO!!!", Toast.LENGTH_LONG);
        //if(!fertig) {
            getButtonStatus();
            if (checkIfBingo()) {
                toast.show();
                fertig = true;
                playSound();
            }
        //}

    }

    private void getSound(){ //Richtet den Sound ein

        setVolumeControlStream(AUSGABE);

        try
        {
            AssetManager am = getAssets();
            mySound = sound.load(am.openFd("sound/Bullshit.mp3"), 1);

        }
        catch (IOException e){

        }


    }

    private void playSound(){ //Spielt den Bingo sound ab

        final float volL =1;
        final float volR =1;
        final int prio = 0;
        final int n=0;
        final float rate =1;
        sound.play(mySound, volL, volR, prio, n, rate);


    }

}
