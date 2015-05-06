
let instrument1 = instru piano ;
let n = 6;
let b = 12;
let r=24;
let theme = { 1dn , 1dn, 1dn , 2d20 , 3d40 } ;
let gamme = { 1dn , 2dn , 3dn , 4dn , 5dn , 6dn, 7dn } ;
let themebartok = transpose 7 { 4b18...};

let theme2locrien = transpose 7 { ...4b15, 5b3, 4b3, 3b27, 4b3,
                         3b3, 4b3, 2b30, 2b3, 3b3, 2b3, 4b3, 3b3, 2b3, 1b15, ?24 } ;
let ionianb3 = scale ionian {,,-,,,,};
let scale1 = scale c ionianb3;

let myxob9b13 = scale myxolydian {,-,,,,-,} ;
let bartokscale = scale myxolydian {,,,,,-,} ;

begin
tempo 120;
play (scale c bartokscale) (instru flute) themebartok;
play (scale c lydian) (instru flute) theme2locrien;
end;