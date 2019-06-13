require "CMessageBox"

now_number   = 0
first_number = 0
input_number = 0
op           = ""
point        = false

function update_screen()
end

function set_input_number(set_number)
   input_number = set_number
end

function on_number_click(number)
   input_number = input_number*10 + number
end

function on_op_click(set_op)
   op = set_op
   first_number = input_number
   input_number = 0
   update_screen()
end

function on_EQ_click()
   if(op == "+")then
      input_number = first_number + input_number
   elseif(op == "-") then
      input_number = first_number - input_number
   elseif(op == "*") then
      input_number = first_number * input_number
   elseif(op == "/") then
      input_number = first_number / input_number
   else
      error("op is "..op..[[, but want get "*", "+", "-" or "/"]])
   end

   first_number = 0
   op = ""
   update_screen()
end

function on_AC_click()
   op = ""
   first_number = 0
   input_number = 0
   update_screen()
end

function on_C_click()
   input_number = 0
end


function on_back_click()
   local ret = CMessageBox([[這項功能尚未完成，也許你可以自行完成。
你可以嘗試將extend-1.txt改成extend.txt看看，這也許能幫助你完成這項功能。]])
   print(ret)
end


pcall(dofile,"extend.txt")