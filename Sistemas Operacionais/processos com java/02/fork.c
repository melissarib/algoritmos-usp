#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()/* P */{

  pid_t c1, c2;

  c2 = 0;

  c1 = fork(); /* fork 1 */
  if (c1 == 0)
    c2 = fork(); /* fork 2 */

  fork(); /* fork 3 */
  if (c2 > 0)
    fork(); /* fork 4 */

  return(0);
}


/*

       c1
(P) -> fork ---> (F1) ---> fork ---> (F3)
        |                   |
        |                   v
        |                  (F3)
        |
        |
        v       c2
       (F1) -> fork -------> (F2) -----> fork ------> (F3) ------> fork -----> (F4)
                |                          |                        |
                |                          |                        |
                |                          v                        v
                |                         (F3) -> fork -> (F4)     (F4)
                |                                  |
                v                                  |
              (F2) -> fork -> (F3)                 v
                       |                          (F4)
                       |
                       |
                       |
                       v
                      (F3)

*/
