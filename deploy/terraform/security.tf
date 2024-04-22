resource "aws_security_group" "apptemplate_sg" {
  name        = "apptemplate_sg"
  description = "apptemplate security group"
  vpc_id      = aws_vpc.apptemplate_vpc_1.id

  tags = {
    "Name" = "apptemplate_sg"
  }
}

resource "aws_security_group_rule" "public_out" {
  type        = "egress"
  from_port   = 0
  to_port     = 0
  protocol    = "-1" # qualquer tipo ssh,..
  cidr_blocks = ["0.0.0.0/0"]

  security_group_id = aws_security_group.apptemplate_sg.id
}

resource "aws_security_group_rule" "public_in_ssh" {
  type              = "ingress"
  from_port         = 22
  to_port           = 22
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.apptemplate_sg.id
}

resource "aws_key_pair" "apptemplate_key" {
  key_name   = "apptemplate_key"
  public_key = file("~/.ssh/apptemplate_key.pub")
}

resource "aws_security_group_rule" "public_in_http" {
  type              = "ingress"
  from_port         = 80
  to_port           = 80
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.apptemplate_sg.id
}