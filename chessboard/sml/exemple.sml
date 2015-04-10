
let instrument1 = instru piano ;
let n = 6;
let b = 12;
let r=24;
let theme = { 1dn , 1dn, 1dn , 2d20 , 3d40 } ;
let gamme = { 1dn , 2dn , 3dn , 4dn , 5dn , 6dn, 7dn } ;
let ionianb3 = scale ionian {,,-,,,,};
let scale1 = scale c ionianb3;
let bartokscale = scale lydian {,,,,,-,} ;
let note = { 1c500 } ;
begin
tempo 120;
play (scale c lydian) (ens instrument1 (instru flute)) note;
end;