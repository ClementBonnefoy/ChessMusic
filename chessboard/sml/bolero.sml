/* Ravel - Boléro */

/* Themes */ 

let themeA_1= transpose 7 { 1d18 , 7c3, 1d3, 2d3, 1d3, 7c3, 6c3, 1d6, 1d3, 6c3, 1d18,
                          7c3, 1d3, 6c3, 5c3, 3c3, 4c3, 5c27, 4c3, 3c3, 2c3, 3c3, 4c3,
                         5c3, 6c3, 5c27, 6c3, 7c3, 6c3, 5c3, 4c3, 3c3, 2c3, 3c3, 2c3,
                         1c12, 1c3, 2c3, 3c6, 4c6, 2c12, 5c54, ?6} ;
let themeA_2= transpose 7 { 2d21, 1d3, 7c3,
                         6c3, 7c3, 1d3, 2d3, 1d3, 7c9, 1d3, 7c3, 6c3, 1d3, 7c3, 6c3, 
                         4c9, 4c3, 4c3, 4c6, 6c6, 1d3, 6c3, 7c3, 5c3, 4c6, 4c3, 4c3,
                         4c6, 6c6, 7c3, 5c3, 6c3, 4c3, 2c6, 2c3, 1c3, 2c18, 2c3, 2c3,
                         2c6, 4c6, 6c3, 4c3, 5c3, 3c3, 2c6, 2c3, 1c3, 2c18, 2c3, 1c3,
                         2c6, 3c3, 4c3, 5c27, 4c3, 3c3, 2c3, 1c6, ?6 } ; 
                         
let theme2myxo = transpose 7 { 7c15, 6c3, 5c3, 4c3, 7c3, 1d3, 6c3, 5c3, 7c6, 6c3, 5c3, 7c12,
                          6c3, 7c3, 6c3, 5c18, 4c3, 3c3, 2c3, 3c12...} ;

let theme2myxob9b13 = transpose 7 { ...3c12, ?6 , 7c6, 1d6,
                         2d12, 2d12, 2d6, 2d6, 2d6, 2d4, 2d4, 2d4, 2d6, 1d3, 7c3, 2d6,
                         1d3, 7c3, 2d3, 1d3, 7c3, 6c3, 5c3, 4c3, 3c48, ?6 };
let themebartok = transpose 7 { 2c18, 3c6, 2c3, 3c3, 4c18, 5c6, 6c6, 4c4, 5c4,
                         3c4, 2c3, 3c3, 2c3, 1c9, 7b21, 1c3, 7b3, 1c3, 2c3, 3c3, 2c3, 1c3,
                         2c3, 1c3, 7b3, 6b3, 7b3, 6b3, 5b12, 4b18...};

let theme2phrygian = transpose 7 { ...4b15, 5b3, 4b3, 3b27, 4b3,
                         3b3, 4b3, 2b30, 2b3, 3b3, 2b3, 4b3, 3b3, 2b3, 1b15, ?24 } ;
                         
let rythme1 = { 40a6, 40a2, 40a2, 40a2, 40a6, 40a2, 40a2, 40a2, 40a6, 40a6 };
let rythme2 = {	40a6, 40a2, 40a2, 40a2, 40a6, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2 };
let rythme = sequence rythme1 rythme2;		
	
/* Harmonies */
		
let harmoBasse1_1 = { 1b6, ?6, ?12, 5a6 , ?6 };
let harmoBasse1_2 = { 1b6, ?6 , ?12, 5a6, ?6 };
let harmoBasse2_1 = { ?24, 5b6 , ?6 };
let harmoBasse2_2 = { ?24, 5b6, 5b6};
let harmoAigu1 = { 1b6 , ?6,  5b6 , ?6 ,  ?12 };
let harmoAigu2 = { ?12, 5c6 , ?6 , ?12 };
let harmo1 = chord (chord harmoBasse1_1 harmoBasse1_2) (chord harmoAigu1 harmoAigu2);
let harmo2 = chord (chord harmoBasse2_1 harmoBasse2_2) (chord harmoAigu1 harmoAigu2);
let harmo = sequence harmo1 harmo2 ;

/* Gammes */

let myxob9b13 = scale myxolydian {,-,,,,-,} ;
let bartokscale = scale myxolydian {,,,,,-,} ;

/* Instruments */

let instru1= instru guitar 50;
let pizzicato=instru pizzicato;
let piano=instru piano;
let flute= instru flute;
let trumpet = instru trumpet;
let clarinet = instru clarinet;
let brassSection = instru brassSection;
let oboe = instru oboe;
let frenchHorn = instru frenchHorn 40;
let drum = instru drumKit 40;
let instru_harmo2 = ens pizzicato instru1;
let instru_theme2 = trumpet;

/* Corps */

begin
tempo 120;
play (scale c ionian) (sequence (chord (drum (repeat 1 rythme)) (pizzicato (repeat 1 harmo))) (chord (drum (repeat 4 rythme)) (chord (pizzicato (repeat 4 harmo)) (flute themeA_1))));
play (scale c ionian) (chord (drum (repeat 5 rythme)) (chord (pizzicato (repeat 5 harmo)) (flute themeA_2)));
play (scale c myxolydian) (chord (drum (sequence rythme rythme1)) (chord (pizzicato (sequence harmo harmo1)) (flute theme2myxo)));
play (scale c myxob9b13) (chord (drum (sequence rythme2 (repeat 2 rythme))) (chord (pizzicato (sequence harmo2 (repeat 2 harmo))) (flute theme2myxob9b13)));
play (scale c bartokscale) (chord (drum (sequence (repeat 2 rythme) rythme1)) (chord (pizzicato (sequence (repeat 2 harmo) harmo1)) (flute themebartok)));
play (scale c phrygian) (chord (drum (sequence rythme2 (repeat 2 rythme))) (chord (pizzicato (sequence harmo2 (repeat 2 harmo))) (flute theme2phrygian)));*/
play (scale c ionian) (sequence (chord (drum (repeat 1 rythme)) (instru_harmo2 (repeat 1 harmo))) (chord (drum (repeat 4 rythme)) (chord (instru_harmo2 (repeat 4 harmo)) (instru_theme2 themeA_1))));
play (scale c ionian) (chord (drum (repeat 5 rythme)) (chord (instru_harmo2 (repeat 5 harmo)) (instru_theme2 themeA_2)));
play (scale c myxolydian) (chord (drum (sequence rythme rythme1)) (chord (instru_harmo2 (sequence harmo harmo1)) (instru_theme2 theme2myxo)));
play (scale c myxob9b13) (chord (drum (sequence rythme2 (repeat 2 rythme))) (chord (instru_harmo2 (sequence harmo2 (repeat 2 harmo))) (instru_theme2 theme2myxob9b13)));
play (scale c bartokscale) (chord (drum (sequence (repeat 2 rythme) rythme1)) (chord (instru_harmo2 (sequence (repeat 2 harmo) harmo1)) (instru_theme2 themebartok)));
play (scale c phrygian) (chord (drum (sequence rythme2 (repeat 2 rythme))) (chord (instru_harmo2 (sequence harmo2 (repeat 2 harmo))) (instru_theme2 theme2phrygian)));
end;