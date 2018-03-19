package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Subjects extends AppCompatActivity {

    float cgpa=0,sum=0;
    int SemNumber,BranchNumber;
    Button btnNextsem,btnResult;
    String g[]={"S","A","B","C","D","E","F"};
    TextView tvSlot1,tvSlot2,tvSlot3,tvSlot4,tvSlot5,tvSlot6,tvSlot7,tvSlot8,tvSlot9,tvSlot10;
    Spinner SpinnerSlot1,SpinnerSlot2,SpinnerSlot3,SpinnerSlot4,SpinnerSlot5,SpinnerSlot6,SpinnerSlot7,SpinnerSlot8,SpinnerSlot9,SpinnerSlot10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        //reference..........................................................
        btnResult = findViewById(R.id.btn_Result);
        btnNextsem = findViewById(R.id.btn_Next_Sem_Result);


        SpinnerSlot1 = findViewById(R.id.spinner_slot1);
        SpinnerSlot2 = findViewById(R.id.spinner_slot2);
        SpinnerSlot3 = findViewById(R.id.spinner_slot3);
        SpinnerSlot4 = findViewById(R.id.spinner_slot4);
        SpinnerSlot5 = findViewById(R.id.spinner_slot5);
        SpinnerSlot6 = findViewById(R.id.spinner_slot6);
        SpinnerSlot7 = findViewById(R.id.spinner_slot7);
        SpinnerSlot8 = findViewById(R.id.spinner_slot8);
        SpinnerSlot9 = findViewById(R.id.spinner_slot9);
        SpinnerSlot10 = findViewById(R.id.spinner_slot10);

        tvSlot1 = findViewById(R.id.tv_slot1);
        tvSlot2 = findViewById(R.id.tv_slot2);
        tvSlot3 = findViewById(R.id.tv_slot3);
        tvSlot4 = findViewById(R.id.tv_slot4);
        tvSlot5 = findViewById(R.id.tv_slot5);
        tvSlot6 = findViewById(R.id.tv_slot6);
        tvSlot7 = findViewById(R.id.tv_slot7);
        tvSlot8 = findViewById(R.id.tv_slot8);
        tvSlot9 = findViewById(R.id.tv_slot9);
        tvSlot10 = findViewById(R.id.tv_slot10);


        //...............................................................

        ArrayAdapter<String> adptr1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot1.setAdapter(adptr1);
        ArrayAdapter<String> adptr2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot2.setAdapter(adptr2);
        ArrayAdapter<String> adptr3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot3.setAdapter(adptr3);
        ArrayAdapter<String> adptr4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot4.setAdapter(adptr4);
        ArrayAdapter<String> adptr5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot5.setAdapter(adptr5);
        ArrayAdapter<String> adptr6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot6.setAdapter(adptr6);
        ArrayAdapter<String> adptr7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot7.setAdapter(adptr7);
        ArrayAdapter<String> adptr8 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot8.setAdapter(adptr8);
        ArrayAdapter<String> adptr9 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot9.setAdapter(adptr9);
        ArrayAdapter<String> adptr10 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot10.setAdapter(adptr10);
        //Getting semester id from select semester activity
        SemNumber = getIntent().getExtras().getInt("MY_SEM");
        Toast.makeText(this, "sem=" + SemNumber, Toast.LENGTH_SHORT).show();


        BranchNumber = getIntent().getExtras().getInt("MY_BRANCH");
        Toast.makeText(this, "branch=" + BranchNumber, Toast.LENGTH_SHORT).show();


        setSubject(SemNumber, BranchNumber);


        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
                a1 = sswitch(SpinnerSlot1);
                a2 = sswitch(SpinnerSlot2);
                a3 = sswitch(SpinnerSlot3);
                a4 = sswitch(SpinnerSlot4);
                a5 = sswitch(SpinnerSlot5);
                a6 = sswitch(SpinnerSlot6);
                a7 = sswitch(SpinnerSlot7);
                a8 = sswitch(SpinnerSlot8);
                a9 = sswitch(SpinnerSlot9);
                a10 = sswitch(SpinnerSlot10);


                // tvSemesterInSum.setText(""+number);
                if (BranchNumber == 1) {
                    //CALCULATING CGPA OF COMPUTER
                    if (SemNumber == 1) {
                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 2) + (a6 * 4);
                        cgpa = sum / 21;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();


                    } else if (SemNumber == 2) {
                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 4) + (a6 * 5) + (a7 * 3) + (a8 * 3) + (a9 * 2) + (a10 * 2);
                        cgpa = sum / 34;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();


                    } else if (SemNumber == 3) {

                        sum += (a1 * 4) + (a2 * 5) + (a3 * 4) + (a4 * 4) + (a5 * 3) + (a6 * 3) + (a7 * 3) + (a8 * 3);
                        cgpa = sum / 29;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();

                    } else if (SemNumber == 4) {

                        sum += (a1 * 4) + (a2 * 4) + (a3 * 5) + (a4 * 4) + (a5 * 3) + (a6 * 3) + (a7 * 3) + (a8 * 5);
                        cgpa = sum / 31;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();


                    } else if (SemNumber == 5) {
                        sum += (a1 * 4) + (a2 * 4) + (a3 * 4) + (a4 * 4) + (a5 * 2) + (a6 * 2) + (a7 * 2) + (a8 * 3);
                        cgpa = sum / 25;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();

                    } else if (SemNumber == 6) {
                        sum += (a1 * 5) + (a2 * 4) + (a3 * 5) + (a4 * 5) + (a5 * 3) + (a6 * 3) + (a7 * 10);
                        cgpa = sum / 35;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();

                    }
                } else if (BranchNumber == 2) {
                    Toast.makeText(Subjects.this, "Cgpa of civil", Toast.LENGTH_SHORT).show();  //calculate cgpa of
                    if (SemNumber==1){
                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 2) + (a6 * 3) + (a7 * 4);
                        cgpa = sum / 21;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();
                    }else if (SemNumber==2){
                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 4) + (a6 * 5) + (a7 * 3)+(a8*3)+(a9*2)+(a1*20);
                        cgpa = sum / 34;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();
                    }else if (SemNumber==3){
                        sum += (a1 * 6) + (a2 * 6) + (a3 * 6) + (a4 * 3) + (a5 * 3) + (a6 * 2) + (a7 * 2)+(a8*2);
                        cgpa = sum / 30;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();
                    }else if (SemNumber==4){
                        sum += (a1 * 5) + (a2 * 5) + (a3 * 5) + (a4 * 4) + (a5 * 2) + (a6 * 2) + (a7 * 3)+(a8*5);
                        cgpa = sum / 31;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();
                    }else if (SemNumber==5){
                        sum += (a1 * 4) + (a2 * 5) + (a3 * 2) + (a4 * 3) + (a5 * 4) + (a6 * 2) + (a7 * 3)+(a8*2);
                        cgpa = sum / 21;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();
                    }else if (SemNumber==6){
                        sum += (a1 * 4) + (a2 * 4) + (a3 * 5) + (a4 * 4) + (a5 * 3) + (a6 * 2) + (a7 * 2)+(a8*10);
                        cgpa = sum / 21;
                        Toast.makeText(Subjects.this, "CGPA=" + cgpa, Toast.LENGTH_SHORT).show();
                    }


                }
                Intent n15 = new Intent(getApplicationContext(), Result.class);
                n15.putExtra("reslt", cgpa);
                startActivity(n15);


                // number++;
            }
        });

        btnNextsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (SemNumber < 6) {

                    View bb1 = findViewById(R.id.lay_slot10);
                    bb1.setVisibility(View.VISIBLE);
                    View bb2 = findViewById(R.id.lay_slot9);
                    bb2.setVisibility(View.VISIBLE);
                    View bb3 = findViewById(R.id.lay_slot8);
                    bb3.setVisibility(View.VISIBLE);
                    View bb4 = findViewById(R.id.lay_slot7);
                    bb4.setVisibility(View.VISIBLE);
                    SemNumber++;
                    setSubject(SemNumber, BranchNumber);

                }
                else {
                    Toast.makeText(Subjects.this, "Course completed...!", Toast.LENGTH_SHORT).show();
                }


                //dump code
                //finish();
                //startActivity(getIntent());


            }
        });

    }





    //spinner data check..................................................

    public int sswitch(Spinner v){

        int z=0;
        String gradeWeb=v.getSelectedItem().toString();

        switch (gradeWeb){
            case "S":z=10;break;
            case "A":z= 9;break;
            case "B":z=8;break;
            case "C":z=7;break;
            case "D":z=6;break;
            case "E":z=5;break;
            case "F":return 0;



        }

    return z;

    }



//set subjects for layouts...........................................
    public void setSubject(int SID,int BID){


        if (BID == 1) {
            if (SID == 1) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                View b3 = findViewById(R.id.lay_slot8);
                b3.setVisibility(View.GONE);
                View b4 = findViewById(R.id.lay_slot7);
                b4.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_english_i));
                tvSlot2.setText(getString(R.string.sub_mathematics_i));
                tvSlot3.setText(getString(R.string.sub_physics_i));
                tvSlot4.setText(getString(R.string.sub_chemistry_i));
                tvSlot5.setText(getString(R.string.sub_hpe));
                tvSlot6.setText(getString(R.string.sub_computing_fund));
            } else if (SID == 2) {
                tvSlot1.setText(getString(R.string.sub_english_ii));
                tvSlot2.setText(getString(R.string.sub_maths_ii));
                tvSlot3.setText(getString(R.string.sub_physics_ii));
                tvSlot4.setText(getString(R.string.sub_chemistry_ii));
                tvSlot5.setText(getString(R.string.sub_programming_in_c));
                tvSlot6.setText(getString(R.string.sub_engineering_graphics));
                tvSlot7.setText(getString(R.string.sub_Workshop_practice));
                tvSlot8.setText(getString(R.string.sub_sub_science_lab));
                tvSlot9.setText(getString(R.string.sub_programmin_in_c_lab));
                tvSlot10.setText(getString(R.string.sub_life_skill));
            } else if (SID == 3) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_dcp));
                tvSlot2.setText(getString(R.string.sub__oop_C));
                tvSlot3.setText(getString(R.string.sub_ca));
                tvSlot4.setText(getString(R.string.sub_dbms));
                tvSlot5.setText(getString(R.string.sub_evs));
                tvSlot6.setText(getString(R.string.sub_dcp_lab));
                tvSlot7.setText(getString(R.string.sub_oop_lab));
                tvSlot8.setText(getText(R.string.sub_dbms_lab));
            } else if (SID == 4) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_data_communication));
                tvSlot2.setText(getString(R.string.sub_operating_system));
                tvSlot3.setText(getString(R.string.sub_data_structure));
                tvSlot4.setText(getString(R.string.sub_Computer_System_Hardware));
                tvSlot5.setText(getString(R.string.sub_system_admin_lab));
                tvSlot6.setText(getString(R.string.sub_data_structure_lab));
                tvSlot7.setText(getString(R.string.sub_csh_lab));
                tvSlot8.setText(getText(R.string.sub_app_dev_lab));
            } else if (SID == 5) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_softare_eng));
                tvSlot2.setText(getString(R.string.sub_web_programmin));
                tvSlot3.setText(getString(R.string.sub_microprocessor));
                tvSlot4.setText(getString(R.string.sub_elective));
                tvSlot5.setText(getString(R.string.sub_web_programmin));
                tvSlot6.setText(getString(R.string.sub_mp_lab));
                tvSlot7.setText(getString(R.string.sub_industrial_visit));
                tvSlot8.setText(getText(R.string.sub_computer_networks));

            } else if (SID == 6) {
                tvSlot1.setText(getString(R.string.sub_mc));
                tvSlot2.setText(getString(R.string.sub_cn));
                tvSlot3.setText(getString(R.string.sub_smart_device_programmin));
                tvSlot4.setText(getString(R.string.sub_elective));
                tvSlot5.setText(getString(R.string.sub_mc_lab));
                tvSlot6.setText(getString(R.string.sub_sdp_lab));
                tvSlot7.setText(getString(R.string.sub_project_seminar));
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                View b3 = findViewById(R.id.lay_slot8);
                b3.setVisibility(View.GONE);
            } else {
                Toast.makeText(this, "Course completed..", Toast.LENGTH_SHORT).show();
            }
        }else if (BID==2){
            Toast.makeText(this, "civil", Toast.LENGTH_SHORT).show();

            if (SID == 1) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                View b3 = findViewById(R.id.lay_slot8);
                b3.setVisibility(View.GONE);
                View b4 = findViewById(R.id.lay_slot7);
                b4.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_english_i));
                tvSlot2.setText(getString(R.string.sub_mathematics_i));
                tvSlot3.setText(getString(R.string.sub_physics_i));
                tvSlot4.setText(getString(R.string.sub_chemistry_i));
                tvSlot5.setText(getString(R.string.sub_hpe));
                tvSlot6.setText(getString(R.string.sub_computing_fund));
            } else if (SID == 2) {
                tvSlot1.setText(getString(R.string.sub_english_ii));
                tvSlot2.setText(getString(R.string.sub_maths_ii));
                tvSlot3.setText(getString(R.string.sub_physics_ii));
                tvSlot4.setText(getString(R.string.sub_chemistry_ii));
                tvSlot5.setText(getString(R.string.sub_surveying_i));
                tvSlot6.setText(getString(R.string.sub_engineering_graphics));
                tvSlot7.setText(getString(R.string.sub_Workshop_practice));
                tvSlot8.setText(getString(R.string.sub_science_lab_ii));
                tvSlot9.setText(getString(R.string.sub_survey_practical_i));
                tvSlot10.setText(getString(R.string.sub_life_skill));
            } else if (SID == 3) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_theory_structures_i));
                tvSlot2.setText(getString(R.string.sub_surveying_ii));
                tvSlot3.setText(getString(R.string.sub_construction_materials_eng));
                tvSlot4.setText(getString(R.string.sub_evs));
                tvSlot5.setText(getString(R.string.sub_building_planning_and_drawing));
                tvSlot6.setText(getString(R.string.sub_survey_practical_ii));
                tvSlot7.setText(getString(R.string.sub_construction_lab));
                tvSlot8.setText(getText(R.string.sub_concrete_lab));
            } else if (SID == 4) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_theory_structures_ii));
                tvSlot2.setText(getString(R.string.sub_quantity_surveying_i));
                tvSlot3.setText(getString(R.string.sub_hydraulics));
                tvSlot4.setText(getString(R.string.sub_irrigation_engineering));
                tvSlot5.setText(getString(R.string.sub_survey_practical_iii));
                tvSlot6.setText(getString(R.string.sub_hydraulics_lab));
                tvSlot7.setText(getString(R.string.sub_computer_aided_drafting_lab));
                tvSlot8.setText(getText(R.string.sub_mini_project));
            } else if (SID == 5) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_construction_management_and_safty_engineering));
                tvSlot2.setText(getString(R.string.sub_structural_design_i));
                tvSlot3.setText(getString(R.string.sub_geotechnical_engineering));
                tvSlot4.setText(getString(R.string.sub_quantity_surveying_ii));
                tvSlot5.setText(getString(R.string.sub_elective));
                tvSlot6.setText(getString(R.string.sub_geotechnical_engineering_lab));
                tvSlot7.setText(getString(R.string.sub_computer_appication_lab));
                tvSlot8.setText(getText(R.string.sub_industrial_visit));

            } else if (SID == 6) {
                tvSlot1.setText(getString(R.string.sub_structural_design_ii));
                tvSlot2.setText(getString(R.string.sub_environmental_engineering));
                tvSlot3.setText(getString(R.string.sub_transportation_engineering));
                tvSlot4.setText(getString(R.string.sub_elective));
                tvSlot5.setText(getString(R.string.sub_structural_and_irrigation_engineering_drawing));
                tvSlot6.setText(getString(R.string.sub_material_lesting_lab));
                tvSlot7.setText(getString(R.string.sub_environmental_engineering_lab));
                tvSlot4.setText(getString(R.string.sub_project_seminar));
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);

            }


        }

        }






    @Override
    public void onResume(){
        super.onResume();
      // setSubject(SemNumber,BranchNumber);
        sum=0;
        cgpa=0;

    }




}

