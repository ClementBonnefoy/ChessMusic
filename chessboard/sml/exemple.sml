
let instrument1 = instru piano ;
let n = 6;
let b = 12;
let r=24;
let theme = { 1dn , 1dn, 1dn , 2d20 , 3d40 } ;
let ionianb3 = scale ionian {,,-,,,,};
let scale1 = scale c ionianb3;
begin
tempo 120;
play scale1 (ens instrument1 (instru flute)) theme;
end;