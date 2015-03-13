
let instrument1 = piano ;
let theme = { 1d6 , 1d6, 1d6 , 2d6 , 3d12 , 2d12 , 1d6 , 3d6 , 2d6 , 2d6 , 1d24 } ;
begin
tempo 120;
play (scale c ionian) instrument1 theme;
end;