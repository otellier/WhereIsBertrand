import os

i = 1
while i < 18:
	os.rename('frame-' + `i` + '.gif', 'frame_' + `i` + '.gif')
	i += 1
