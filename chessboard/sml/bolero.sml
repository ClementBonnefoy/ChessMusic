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

let theme2locrien = transpose 7 { ...4b15, 5b3, 4b3, 3b27, 4b3,
                         3b3, 4b3, 2b30, 2b3, 3b3, 2b3, 4b3, 3b3, 2b3, 1b15, ?24 } ;
                         
let rythme = { 40a6, 40a2, 40a2, 40a2, 40a6, 40a2, 40a2, 40a2, 40a6, 40a6,
					40a6, 40a2, 40a2, 40a2, 40a6, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2, 40a2};
			
/* Harmonies */
		
let harmoBasse1_1 = { 1b12, ?12, 5a12 };
let harmoBasse1_2 = { 1b12, ?12, 5a6, ?6 };
let harmoBasse2_1 = { ?24, 5b12 };
let harmoBasse2_2 = { ?24, 5b6, 5b6};
let harmoAigu1 = { 5b12, 5b12, ?12 };
let harmoAigu2 = { ?12, 5c12, ?12 };
let harmo1 = chord (chord harmoBasse1_1 harmoBasse2_1) (chord harmoAigu1 harmoAigu2);
let harmo2 = chord (chord harmoBasse2_1 harmoBasse2_2) (chord harmoAigu1 harmoAigu2);
let harmo = sequence harmo1 harmo2 ;

let myxob9b13 = scale myxolydian {,-,,,,-,} ;
let bartokscale = scale myxolydian {,,,,,-,} ;

/* Instruments */

let instrument1=piano;
let pizzicato=instru pizzicato;
let piano=instru piano;
let flute= instru flute;
let drum = instru drumKit 30;

/* Corps */

begin
tempo 120;
play (scale c ionian) drum rythme;
play (scale c ionian) (chord (drum rythme) (chord (pizzicato (repeat 4 harmo)) (flute themeA_1)));
play (scale c ionian) (chord (pizzicato (repeat 4 harmo)) (flute themeA_2));
play (scale c myxolydian) (chord (pizzicato (sequence harmo harmo1)) (flute theme2myxo));
play (scale c myxob9b13) (chord (pizzicato (sequence harmo2 (repeat 2 harmo))) (flute theme2myxob9b13));
play (scale c bartokscale) (chord (pizzicato (sequence (repeat 2 harmo) harmo1)) (flute themebartok));
play (scale c phrygian) (chord (pizzicato (sequence harmo2 (repeat 2 harmo))) (flute theme2locrien));
end;